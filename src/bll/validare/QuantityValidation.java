package bll.validare;

import javax.swing.JOptionPane;

import model.OrderedProduct;

public class QuantityValidation implements Validation_I<OrderedProduct>{
	
	private static final int MIN_Q = 0;
	
	@Override
	public void validate(OrderedProduct t) {
		// TODO Auto-generated method stub
		
		int stoc=t.getQuantity();
		if(stoc< MIN_Q){
			JOptionPane.showMessageDialog(null,
					"Introduceti o valoare pozitiva!");
			throw new IllegalArgumentException("Introduceti o valoare pozitiva!");
		}
	}

}
