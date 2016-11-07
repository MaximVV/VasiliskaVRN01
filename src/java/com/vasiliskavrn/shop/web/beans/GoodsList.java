package com.vasiliskavrn.shop.web.beans;

import com.vasiliskavrn.shop.web.db.Database;
import com.vasiliskavrn.shop.web.enums.SearchType;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import java.awt.Image;



public class GoodsList {

    private ArrayList<Goods> goodsList = new ArrayList<Goods>();

    private ArrayList<Goods> getGoods(String str) {
        
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn = null;
        
        try {
            conn = Database.getConnection();                       
            stmt = conn.createStatement();
            
            System.out.println(str);
            rs = stmt.executeQuery(str);
            while (rs.next()) {
                Goods goods = new Goods();
                goods.setId(rs.getLong("goods_id"));
                goods.setArticle(rs.getString("goods_art"));
                goods.setclothG(rs.getString("cloth_name_one"));
                goods.setSex(rs.getString("sex_name"));
                goods.setCountryMade(rs.getString("prod_country"));
                goods.setPrice(rs.getString("price_val"));
                goods.setFirme(rs.getString("firme_name"));
                goods.setColor(rs.getString("col_name"));
                goods.setSize(rs.getString("size_name"));
                goods.setComposition(rs.getString("comp_name"));
                goods.setCountryBrand(rs.getString("brand_country"));
                goods.setImage(rs.getBytes("image_cotnent"));
                goodsList.add(goods);
            }

        } catch (SQLException ex) {
             System.out.println(str);
             System.out.println(ex);
            Logger.getLogger(GoodsList.class.getName()).log(Level.SEVERE, null, ex);
           
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(GoodsList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return goodsList;
    }

    public ArrayList<Goods> getAllGoods() {
        if (!goodsList.isEmpty()) {
            return goodsList;
        } else {
            return getGoods("SELECT g.goods_id,g.goods_art,\n" +
"	   ct.cloth_name_one,\n" +
"       s.sex_name,\n" +
"       pr.prod_country,\n" +
"       cm.comp_name,\n" +
"       p.price_val,\n" +
"       f.firme_name,\n" +
"       c.col_name,\n" +
"       sz.size_name,\n" +
"       b.brand_country,\n" +
"       i.image_cotnent\n" +
"  FROM vasiliska2016.goods_tab g,\n" +
"       vasiliska2016.cloth_tab ct,\n" +
"	   vasiliska2016.sex_tab   s,\n" +
"       vasiliska2016.producer_tab pr,\n" +
"       vasiliska2016.composition_tab cm,\n" +
"       vasiliska2016.price_tab p,\n" +
"       vasiliska2016.firme_tab f,\n" +
"       vasiliska2016.color_tab c,\n" +
"       vasiliska2016.size_tab  sz,\n" +
"       vasiliska2016.brand_tab  b,\n" +
"       vasiliska2016.image_tab  i\n" +
" WHERE 1=1\n" +
"   and g.goods_cloth = ct.id_cloth_tab\n" +
"   and g.goods_sex = s.id_sex_tab\n" +
"   and g.goods_produser = pr.id_producer_tab\n" +
"   and g.goods_comp = cm.id_comp_tab\n" +
"   and g.goods_price = p.id_price_tab\n" +
"   and g.goods_firm = f.id_firme_table\n" +
"   and g.goods_color = c.id_color_tab\n" +
"   and g.goods_size = sz.id_size_table\n" +
"   and g.goods_coun_br = b.id_brand_tab\n" +
"   and g.goods_image = i.id_image_tab\n" +
"   order by ct.cloth_name_one limit 0,5;");
        }
    }
    
    
    public ArrayList<Goods> getGoodsByCloth(long id) {
              return getGoods("SELECT g.goods_id,g.goods_art,\n" +
"	   ct.cloth_name_one,\n" +
"       s.sex_name,\n" +
"       pr.prod_country,\n" +
"       cm.comp_name,\n" +
"       p.price_val,\n" +
"       f.firme_name,\n" +
"       c.col_name,\n" +
"       sz.size_name,\n" +
"       b.brand_country,\n" +
"       i.image_cotnent\n" +
"  FROM vasiliska2016.goods_tab g,\n" +
"       vasiliska2016.cloth_tab ct,\n" +
"	   vasiliska2016.sex_tab   s,\n" +
"       vasiliska2016.producer_tab pr,\n" +
"       vasiliska2016.composition_tab cm,\n" +
"       vasiliska2016.price_tab p,\n" +
"       vasiliska2016.firme_tab f,\n" +
"       vasiliska2016.color_tab c,\n" +
"       vasiliska2016.size_tab  sz,\n" +
"       vasiliska2016.brand_tab  b,\n" +
"       vasiliska2016.image_tab  i\n" +
" WHERE 1=1\n" +
"   and ct.id_cloth_tab = "+ id +" and g.goods_cloth = ct.id_cloth_tab\n" +
"   and g.goods_sex = s.id_sex_tab\n" +
"   and g.goods_produser = pr.id_producer_tab\n" +
"   and g.goods_comp = cm.id_comp_tab\n" +
"   and g.goods_price = p.id_price_tab\n" +
"   and g.goods_firm = f.id_firme_table\n" +
"   and g.goods_color = c.id_color_tab\n" +
"   and g.goods_size = sz.id_size_table\n" +
"   and g.goods_coun_br = b.id_brand_tab\n" +
"   and g.goods_image = i.id_image_tab\n" +
"   order by ct.cloth_name_one limit 0,5;");
        }
    
     public ArrayList<Goods> getGoodsByLetter(String letter) {;
        return getGoods("SELECT g.goods_id,g.goods_art,\n" +
"	   ct.cloth_name_one,\n" +
"       s.sex_name,\n" +
"       pr.prod_country,\n" +
"       cm.comp_name,\n" +
"       p.price_val,\n" +
"       f.firme_name,\n" +
"       c.col_name,\n" +
"       sz.size_name,\n" +
"       b.brand_country,\n" +
"       i.image_cotnent\n" +
"  FROM vasiliska2016.goods_tab g,\n" +
"       vasiliska2016.cloth_tab ct,\n" +
"	   vasiliska2016.sex_tab   s,\n" +
"       vasiliska2016.producer_tab pr,\n" +
"       vasiliska2016.composition_tab cm,\n" +
"       vasiliska2016.price_tab p,\n" +
"       vasiliska2016.firme_tab f,\n" +
"       vasiliska2016.color_tab c,\n" +
"       vasiliska2016.size_tab  sz,\n" +
"       vasiliska2016.brand_tab  b,\n" +
"       vasiliska2016.image_tab  i\n" +
" WHERE 1=1\n" +
"   and g.goods_cloth = ct.id_cloth_tab\n" +
"   and g.goods_sex = s.id_sex_tab\n" +
"   and g.goods_produser = pr.id_producer_tab\n" +
"   and g.goods_comp = cm.id_comp_tab\n" +
"   and g.goods_price = p.id_price_tab\n" +
"   and g.goods_firm = f.id_firme_table\n" +
"   and g.goods_color = c.id_color_tab\n" +
"   and g.goods_size = sz.id_size_table\n" +
"   and g.goods_coun_br = b.id_brand_tab\n" +
"   and g.goods_image = i.id_image_tab\n" +
"   and substr(ct.cloth_name_one,1,1)='"+ letter +"'\n" +
"   order by ct.cloth_name_one limit 0,5;");

    }

    public ArrayList<Goods> getGoodsBySearch(String searchStr, SearchType type) {
        StringBuilder sql = new StringBuilder("SELECT g.goods_id,g.goods_art,\n" +
"	   ct.cloth_name_one,\n" +
"       s.sex_name,\n" +
"       pr.prod_country,\n" +
"       cm.comp_name,\n" +
"       p.price_val,\n" +
"       f.firme_name,\n" +
"       c.col_name,\n" +
"       sz.size_name,\n" +
"       b.brand_country,\n" +
"       i.image_cotnent\n" +
"  FROM vasiliska2016.goods_tab g,\n" +
"       vasiliska2016.cloth_tab ct,\n" +
"	   vasiliska2016.sex_tab   s,\n" +
"       vasiliska2016.producer_tab pr,\n" +
"       vasiliska2016.composition_tab cm,\n" +
"       vasiliska2016.price_tab p,\n" +
"       vasiliska2016.firme_tab f,\n" +
"       vasiliska2016.color_tab c,\n" +
"       vasiliska2016.size_tab  sz,\n" +
"       vasiliska2016.brand_tab  b,\n" +
"       vasiliska2016.image_tab  i\n" +
" WHERE 1=1\n" +
"   and g.goods_cloth = ct.id_cloth_tab\n" +
"   and g.goods_sex = s.id_sex_tab\n" +
"   and g.goods_produser = pr.id_producer_tab\n" +
"   and g.goods_comp = cm.id_comp_tab\n" +
"   and g.goods_price = p.id_price_tab\n" +
"   and g.goods_firm = f.id_firme_table\n" +
"   and g.goods_color = c.id_color_tab\n" +
"   and g.goods_size = sz.id_size_table\n" +
"   and g.goods_coun_br = b.id_brand_tab\n" +
"   and g.goods_image = i.id_image_tab\n");

        if (type == SearchType.FOR_BOY) {
            sql.append(" and s.sex_name='Мальчики' and lower(ct.cloth_name_one) like '%" + searchStr.toLowerCase() + "%' order by ct.cloth_name_one  " );

        } else if (type == SearchType.FOR_GIRL) {
            sql.append(" and s.sex_name='Девочки' and lower(ct.cloth_name_one) like '%" + searchStr.toLowerCase() + "%' order by ct.cloth_name_one  " );
        }
        else if (type == SearchType.FOR_MAN) {
            sql.append(" and s.sex_name='Мужчины' and lower(ct.cloth_name_one) like '%" + searchStr.toLowerCase() + "%' order by ct.cloth_name_one  " );
        }
        else if (type == SearchType.FOR_WOMEN) {
            sql.append(" and s.sex_name='Женщины' and lower(ct.cloth_name_one) like '%" + searchStr.toLowerCase() + "%' order by ct.cloth_name_one  " );
        } 
        else if (type == SearchType.FOR_ALL) {
            sql.append(" and lower(ct.cloth_name_one) like '%" + searchStr.toLowerCase() + "%' order by ct.cloth_name_one  " );
        }
        sql.append(" limit 0,5");

        System.out.println(sql.toString());
        
        return getGoods(sql.toString());


    }
    
    

}
