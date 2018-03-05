package bll.validare;

import javax.swing.JOptionPane;

import model.Product;

public class CantitateMagazinValidation implements Validation_I<Product>{
	public static final int MAX_Q = 10001;
	public static final int MIN_Q = 1;
	@Override
	public void validate(Product t) {
		// TODO Auto-generated method stub
		if(t.getCantitateMagazin()<MIN_Q || t.getCantitateMagazin()>MAX_Q){
			JOptionPane.showMessageDialog(null,
					"Introduceti o valoare pozitiva!");
			throw new IllegalArgumentException(
					"The Product quantity limit is not respected!");
		
		}
	}

}
