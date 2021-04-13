package nwta.paymentreminder.enums;

public enum PaymentStatus {

    IS_WAITING(0, "IsWaiting"),
    PAID(1, "Paid"),
    EXPIRED(2, "Expired");

    private Integer id;
    private String status;

    PaymentStatus(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static PaymentStatus findById(Integer id) {
        for (PaymentStatus status : PaymentStatus.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        return null;
    }
}
