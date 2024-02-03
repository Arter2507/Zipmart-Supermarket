/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.zipmart.mbean.checkout;

import com.zipmart.dto.Cart;
import com.zipmart.dto.CartSessionBeanLocal;
import com.zipmart.ejb.entities.Customers;
import com.zipmart.ejb.entities.Invoices;
import com.zipmart.ejb.entities.OrderDetails;
import com.zipmart.ejb.entities.Orders;
import com.zipmart.ejb.session_beans.CustomerCardFacadeLocal;
import com.zipmart.ejb.session_beans.CustomersFacadeLocal;
import com.zipmart.ejb.session_beans.InvoicesFacadeLocal;
import com.zipmart.ejb.session_beans.OrderDetailsFacadeLocal;
import com.zipmart.ejb.session_beans.OrdersFacadeLocal;
import com.zipmart.ejb.session_beans.ProductsFacadeLocal;
import com.zipmart.mbean.cart.CartBean;
import com.zipmart.mbean.login.LoginManagedBean;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author TUONG
 */
@Named(value = "checkoutBean")
@RequestScoped
public class CheckoutBean {

    @EJB
    private InvoicesFacadeLocal invoicesFacade;

    @EJB
    private ProductsFacadeLocal productsFacade;

    @EJB
    private CustomersFacadeLocal customersFacade;

    @EJB
    private CustomerCardFacadeLocal customerCardFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @EJB
    private OrderDetailsFacadeLocal orderDetailsFacade;

    @EJB
    private CartSessionBeanLocal cartSessionBean;

    @Inject
    private LoginManagedBean l;

    @Inject
    private CartBean cartBean;

    private Long OrderID;
    private String orderDate;
    private String phoneShip;
    private String addressShip;
    private String note;
    private int totalAmount;
    private String orderStatus;
    private Integer userID;
    private Orders order = new Orders();
    private OrderDetails od = new OrderDetails();
    private short selected_payment;
    private Invoices invoice = new Invoices();

    private String message = "";

    public CheckoutBean() {
    }

    public String showStatus(Long orderID) {
        Integer l = 1;
        Orders or = ordersFacade.find(orderID);
        if (or.getStatus() == 0) {
            or.setStatus(1);
        } else {
            or.setStatus(0);
        }
        ordersFacade.edit(or);
        return "order";
    }

    public List<OrderDetails> showAllOrders() throws IOException {
        return orderDetailsFacade.findAll();
    }

    public String showDetails(Long id) throws IOException {
        od = orderDetailsFacade.find(id);
        System.out.println(od);
        return "orderdetail";
    }

    public String insertBillInfor() {
        List<Cart> cartList = cartBean.showCart();
        Customers user = customersFacade.getFindByUsername(l.getUsername());
        System.out.println(user);
        LocalDate today = LocalDate.now();
        Date date = java.sql.Date.valueOf(today);
        order.setCustomerID(user);
        order.setNote(note);
        Orders o = order;
        order.setStatus(1);
        order.setPaymentMethod(selected_payment);
        ordersFacade.create(order);
        for (Cart item : cartList) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrderID(order);
            orderDetails.setQuantity(item.getQuantity());
            orderDetails.setShipAddress(addressShip);
            orderDetails.setOrderDate(date);

            // Tạo đối tượng `Calendar` cho ngày hiện tại
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

// Thêm 3 ngày vào ngày hiện tại
            calendar.add(Calendar.DATE, 3);

// Lấy ngày mới từ `calendar`
            date = calendar.getTime();
            orderDetails.setShipDate(date);
            orderDetails.setUnitPrice(item.getUnit_price());
            orderDetails.setProductID(productsFacade.find(item.getId()));
            orderDetails.setDiscount(item.getDiscout());
            orderDetails.setTotalPrice(item.getTotal_price());
            orderDetailsFacade.create(orderDetails);
        }
        message = "Ordered successfully!";
        return "checkout";

    }

    public ProductsFacadeLocal getProductsFacade() {
        return productsFacade;
    }

    public void setProductsFacade(ProductsFacadeLocal productsFacade) {
        this.productsFacade = productsFacade;
    }

    public CustomersFacadeLocal getCustomersFacade() {
        return customersFacade;
    }

    public void setCustomersFacade(CustomersFacadeLocal customersFacade) {
        this.customersFacade = customersFacade;
    }

    public CustomerCardFacadeLocal getCustomerCardFacade() {
        return customerCardFacade;
    }

    public void setCustomerCardFacade(CustomerCardFacadeLocal customerCardFacade) {
        this.customerCardFacade = customerCardFacade;
    }

    public OrdersFacadeLocal getOrdersFacade() {
        return ordersFacade;
    }

    public void setOrdersFacade(OrdersFacadeLocal ordersFacade) {
        this.ordersFacade = ordersFacade;
    }

    public OrderDetailsFacadeLocal getOrderDetailsFacade() {
        return orderDetailsFacade;
    }

    public void setOrderDetailsFacade(OrderDetailsFacadeLocal orderDetailsFacade) {
        this.orderDetailsFacade = orderDetailsFacade;
    }

    public CartSessionBeanLocal getCartSessionBean() {
        return cartSessionBean;
    }

    public void setCartSessionBean(CartSessionBeanLocal cartSessionBean) {
        this.cartSessionBean = cartSessionBean;
    }

    public LoginManagedBean getL() {
        return l;
    }

    public void setL(LoginManagedBean l) {
        this.l = l;
    }

    public CartBean getCartBean() {
        return cartBean;
    }

    public void setCartBean(CartBean cartBean) {
        this.cartBean = cartBean;
    }

    public Long getOrderID() {
        return OrderID;
    }

    public void setOrderID(Long OrderID) {
        this.OrderID = OrderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPhoneShip() {
        return phoneShip;
    }

    public void setPhoneShip(String phoneShip) {
        this.phoneShip = phoneShip;
    }

    public String getAddressShip() {
        return addressShip;
    }

    public void setAddressShip(String addressShip) {
        this.addressShip = addressShip;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public OrderDetails getOd() {
        return od;
    }

    public void setOd(OrderDetails od) {
        this.od = od;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public short getSelected_payment() {
        return selected_payment;
    }

    public void setSelected_payment(short selected_payment) {
        this.selected_payment = selected_payment;
    }

    public InvoicesFacadeLocal getInvoicesFacade() {
        return invoicesFacade;
    }

    public void setInvoicesFacade(InvoicesFacadeLocal invoicesFacade) {
        this.invoicesFacade = invoicesFacade;
    }

    public Invoices getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoices invoice) {
        this.invoice = invoice;
    }
}
