package com.sqli.microservice.dto;

import java.util.Objects;

import lombok.Data;
@Data
public class PaymentInfo {

    private int amount;
    public PaymentInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(amount, currency, receiptEmail);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentInfo other = (PaymentInfo) obj;
		return amount == other.amount && Objects.equals(currency, other.currency)
				&& Objects.equals(receiptEmail, other.receiptEmail);
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getReceiptEmail() {
		return receiptEmail;
	}
	public void setReceiptEmail(String receiptEmail) {
		this.receiptEmail = receiptEmail;
	}
	private String currency;
    private String receiptEmail;

}
