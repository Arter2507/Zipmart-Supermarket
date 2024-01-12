/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ZipMart.sessionbeans;

import com.ZipMart.entities.BlogFeedBacks;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface BlogFeedBacksFacadeLocal {

    void create(BlogFeedBacks blogFeedBacks);

    void edit(BlogFeedBacks blogFeedBacks);

    void remove(BlogFeedBacks blogFeedBacks);

    BlogFeedBacks find(Object id);

    List<BlogFeedBacks> findAll();

    List<BlogFeedBacks> findRange(int[] range);

    int count();
    
}
