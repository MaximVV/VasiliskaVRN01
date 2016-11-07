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
            return getGoods("SELECT g.goods_id, goods_art, cl.cloth_name_one, f.firme_name\n" +
"                              FROM vasiliska2016.goods_tab  g,\n" +
"                                   vasiliska2016.cloth_tab  cl,\n" +
"                                   vasiliska2016.firme_tab f\n" +
"                             WHERE g.goods_id = cl.id_cloth_tab\n" +
"                               and g.goods_id = f.id_firme_table order by cl.cloth_name_one limit 0,5;");
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
        return getGoods("SELECT g.goods_id, goods_art, cl.cloth_name_one, f.firme_name\n" +
"                              FROM vasiliska2016.goods_tab  g,\n" +
"                                   vasiliska2016.cloth_tab  cl,\n" +
"                                   vasiliska2016.firme_tab f\n" +
"                             WHERE g.goods_id = cl.id_cloth_tab\n" +
"                               and g.goods_id = f.id_firme_table order by cl.cloth_name_one limit 0,5;");

    }

    public ArrayList<Goods> getGoodsBySearch(String searchStr, SearchType type) {
        StringBuilder sql = new StringBuilder("select b.id,b.name,b.isbn,b.page_count,b.publish_year, p.name as publisher, a.fio as author, g.name as genre, b.image from book b "
                + "inner join author a on b.author_id=a.id "
                + "inner join genre g on b.genre_id=g.id "
                + "inner join publisher p on b.publisher_id=p.id ");

        if (type == SearchType.FOR_BOY) {
            sql.append("where lower(a.fio) like '%" + searchStr.toLowerCase() + "%' order by b.name ");

        } else if (type == SearchType.FOR_GIRL) {
            sql.append("where lower(b.name) like '%" + searchStr.toLowerCase() + "%' order by b.name ");
        }
        sql.append("limit 0,5");


        return getGoods(sql.toString());


    }
    
    

}
