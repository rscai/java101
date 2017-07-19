package me.raycai.java101.lecture03;

public class OrderUtil {
    public float calculateTotalPrice(final float unitPrice, final int quantity){
        final float discountThreshold = 99.0F;
        final float discount = 0.98F;
        final float deliveryFee = 5.0F;
        final float plusFee1 = 0.01F;
        final float plusFee2 = 0.02F;
        final float plusFee3 = 0.03F;
        final float plusFee4 = 0.04F;
        final float plusFee5 = 0.05F;
        
        final float minusFee1 = 0.001F;
        final float minusFee2 = 0.002F;
        final float minusFee3 = 0.003F;
        final float minusFee4 = 0.004F;
        final float minusFee5 = 0.005F;
        
        final float taxRate = 0.17F;
        final float insuranceRate = 0.05F;
        
        float totalPrice = unitPrice * quantity;
        if(totalPrice > discountThreshold){
            totalPrice = totalPrice * discount;
            totalPrice = totalPrice - minusFee1;
            totalPrice = totalPrice - minusFee2;
            totalPrice = totalPrice - minusFee3;
            totalPrice = totalPrice - minusFee4;
            totalPrice = totalPrice - minusFee5;
            
        }else{
            totalPrice = totalPrice + deliveryFee;
            totalPrice = totalPrice + plusFee1;
            totalPrice = totalPrice + plusFee2;
            totalPrice = totalPrice + plusFee3;
            totalPrice = totalPrice + plusFee4;
            totalPrice = totalPrice + plusFee5;
        }
        
        totalPrice = totalPrice * insuranceRate;
        totalPrice = totalPrice * taxRate;
        
        return totalPrice;
    }
}
