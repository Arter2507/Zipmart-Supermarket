/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.zipmart.ejb.session_beans;

import com.zipmart.ejb.entities.ThresholdAdjustment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author TUONG
 */
@Local
public interface ThresholdAdjustmentFacadeLocal {

    void create(ThresholdAdjustment thresholdAdjustment);

    void edit(ThresholdAdjustment thresholdAdjustment);

    void remove(ThresholdAdjustment thresholdAdjustment);

    ThresholdAdjustment find(Object id);

    List<ThresholdAdjustment> findAll();

    List<ThresholdAdjustment> findRange(int[] range);

    int count();
    
}
