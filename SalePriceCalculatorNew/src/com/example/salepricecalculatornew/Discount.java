package com.example.salepricecalculatornew;

public class Discount
{
	private double originalPrice,finalPrice, savedPrice;
	private double first_discount;
	private double second_discount;
	private double tax;
	
	public Discount()
	{
		originalPrice = 0;
		first_discount = 0;
		second_discount = 0;
		tax = 0;
		finalPrice = 0;
		savedPrice = 0;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getFirst_discount() {
		return first_discount;
	}

	public void setFirst_discount(double first_discount) {
		this.first_discount = first_discount;
	}

	public double getSecond_discount() {
		return second_discount;
	}

	public void setSecond_discount(double second_discount) {
		this.second_discount = second_discount;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public double getSavedPrice() {
		return savedPrice;
	}

	public void setSavedPrice(double savedPrice) {
		this.savedPrice = savedPrice;
	}
	
}



/*package com.example.salepricecalculator;

public class Discount
{
	private double originalPrice,finalPrice, savedPrice, tax;
	private int first_discount, second_discount;
	
	public Discount()
	{
		originalPrice = 0;
		first_discount = 0;
		second_discount = 0;
		tax = 0;
		finalPrice = 0;
		savedPrice = 0;
	}

	public double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}

	public double getSavedPrice() {
		return savedPrice;
	}

	public void setSavedPrice(double savedPrice) {
		this.savedPrice = savedPrice;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public int getFirst_discount() {
		return first_discount;
	}

	public void setFirst_discount(int first_discount) {
		this.first_discount = first_discount;
	}

	public int getSecond_discount() {
		return second_discount;
	}

	public void setSecond_discount(int second_discount) {
		this.second_discount = second_discount;
	}

	
}
*/