//package com.modelExtends;
//
//
//import java.io.Serializable;
//import java.util.List;
//import java.util.UUID;
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//import org.primefaces.PrimeFaces;
//
//@Named
//@ViewScoped
//public class CrudView implements Serializable {
//
//    private List<Product> products;
//
//    private Product selectedProduct;
//
//    private List<Product> selectedProducts;
//
//    @Inject
//    private ProductService productService;
//
//    @PostConstruct
//    public void init() {
//        this.products = this.productService.getClonedProducts(100);
//    }
//
//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public Product getSelectedProduct() {
//        return selectedProduct;
//    }
//
//    public void setSelectedProduct(Product selectedProduct) {
//        this.selectedProduct = selectedProduct;
//    }
//
//    public List<Product> getSelectedProducts() {
//        return selectedProducts;
//    }
//
//    public void setSelectedProducts(List<Product> selectedProducts) {
//        this.selectedProducts = selectedProducts;
//    }
//
//    public void openNew() {
//        this.selectedProduct = new Product();
//    }
//
//    public void saveProduct() {
//        if (this.selectedProduct.getCode() == null) {
//            this.selectedProduct.setCode(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
//            this.products.add(this.selectedProduct);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
//        }
//        else {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
//        }
//
//        PrimeFaces.current().executeScript("PF('manageEmployeeDialog').hide()");
//        PrimeFaces.current().ajax().update("form:messages", "form:dt-employees");
//    }
//
//    public void deleteProduct() {
//        this.products.remove(this.selectedProduct);
//        this.selectedProducts.remove(this.selectedProduct);
//        this.selectedProduct = null;
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
//        PrimeFaces.current().ajax().update("form:messages", "form:dt-employees");
//    }
//
//    public String getDeleteButtonMessage() {
//        if (hasSelectedProducts()) {
//            int size = this.selectedProducts.size();
//            return size > 1 ? size + " products selected" : "1 product selected";
//        }
//
//        return "Delete";
//    }
//
//    public boolean hasSelectedProducts() {
//        return this.selectedProducts != null && !this.selectedProducts.isEmpty();
//    }
//
//    public void deleteSelectedProducts() {
//        this.products.removeAll(this.selectedProducts);
//        this.selectedProducts = null;
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
//        PrimeFaces.current().ajax().update("form:messages", "form:dt-employees");
//        PrimeFaces.current().executeScript("PF('dtEmployees').clearFilters()");
//    }
//
//}