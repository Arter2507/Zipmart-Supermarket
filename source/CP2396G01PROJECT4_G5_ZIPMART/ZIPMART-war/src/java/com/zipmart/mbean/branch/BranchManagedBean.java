/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.branch;

import com.zipmart.ejb.entities.Branch;
import com.zipmart.ejb.session_beans.BranchFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author phnha
 */
@Named(value = "branchMB")
@SessionScoped
public class BranchManagedBean implements Serializable {

    @EJB
    private BranchFacadeLocal branchFacade;
    
    private Branch branch;
    private List<Branch> listBranch = new ArrayList<>();
    private Date createdate;
    
    public BranchManagedBean() {
        branch = new Branch();
        listBranch = new ArrayList<>();
        branch.setCreatedate(new Date(System.currentTimeMillis()));
        
    }
    
    //Showall
    public List<Branch> showAllBranch(){
        return branchFacade.findAll();
    }
    
    //Add
    public String createBranch() {
        branchFacade.create(branch);
        return "displaybranch?faces-redirect=true";
    }
    
    //show detail customer
    public String showDetailBranchU(long id) {
        branch = branchFacade.find(id);
        return "updatebranch";
    }
    
    public String showDetailCustomerD(long id) {
        branch = branchFacade.find(id);
        return "displaybranchdetail";
    }
    
    //Update
    public String updateBranch() {
        branchFacade.edit(branch);
        return "displaybranch?faces-redirect=true";
    }
    //Delete
    public String deleteBranch(long id) {
        Branch c = branchFacade.find(id);
        branchFacade.remove(c);
        return "displaybranch";
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public List<Branch> getListBranch() {
        return listBranch;
    }

    public void setListBranch(List<Branch> listBranch) {
        this.listBranch = listBranch;
    }

    public BranchFacadeLocal getBranchFacade() {
        return branchFacade;
    }

    public void setBranchFacade(BranchFacadeLocal branchFacade) {
        this.branchFacade = branchFacade;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    
}
