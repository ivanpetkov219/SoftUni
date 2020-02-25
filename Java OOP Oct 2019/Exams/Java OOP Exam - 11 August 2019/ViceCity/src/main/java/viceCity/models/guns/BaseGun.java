package viceCity.models.guns;

import viceCity.common.ExceptionMessages;

public abstract class BaseGun implements Gun {
   private String name;
   private int bulletsPerBarrel;
   private int totalBullets;
   private int barrelCapacity;
   private boolean canFire;

   protected BaseGun(String name, int bulletsPerBarrel, int totalBullets){
       this.setName(name);
       this.setBulletsPerBarrel(bulletsPerBarrel);
       this.setTotalBullets(totalBullets);
       this.barrelCapacity = bulletsPerBarrel;
       this.canFire = true;

   }

    private void setTotalBullets(int totalBullets) {
        if(totalBullets < 0){
            throw new IllegalArgumentException(ExceptionMessages.TOTAL_BULLETS_LESS_THAN_ZERO);
        }
    }

    private void setBulletsPerBarrel(int bulletsPerBarrel) {
        if(bulletsPerBarrel < 0){
            throw new IllegalArgumentException(ExceptionMessages.BULLETS_LESS_THAN_ZERO);
        }
        this.bulletsPerBarrel = bulletsPerBarrel;

    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()){
        throw new NullPointerException(ExceptionMessages.NAME_NULL);
        }
        this.name = name;
    }



    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getBulletsPerBarrel() {
        return this.bulletsPerBarrel;
    }

    protected void decreaseBulletsInBarrel(int shotBullets){
       this.bulletsPerBarrel -= shotBullets;
    }

    @Override
    public boolean canFire() {
        return this.canFire;
    }

    @Override
    public int getTotalBullets() {
        return this.totalBullets;
    }

    @Override
    public abstract int fire();

    protected void reload() {
        if(this.getTotalBullets() >= this.barrelCapacity){
            this.bulletsPerBarrel = barrelCapacity;
            this.totalBullets -= barrelCapacity;
        }else {
            this.setCanFire(false);
        }
    }
    protected int getBarrelCapacity(){
        return this.barrelCapacity;
    }

    protected void setCanFire(boolean canFire){
        this.canFire = canFire;
    }

}
