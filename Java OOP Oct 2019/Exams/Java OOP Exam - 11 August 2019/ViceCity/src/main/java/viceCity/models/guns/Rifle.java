package viceCity.models.guns;

public class Rifle extends BaseGun {
    private static final int INITIAL_BULLETS_PER_BARREL = 50;
    private static final int INITIAL_TOTAL_BULLETS = 500;
    private static final int BULLETS_PER_FIRE = 5;

    public Rifle(String name) {
        super(name, INITIAL_BULLETS_PER_BARREL, INITIAL_TOTAL_BULLETS);
    }

    @Override
    public int fire(){
        int result;

        if(this.canFire() && this.getBulletsPerBarrel() >= BULLETS_PER_FIRE) {
            result = BULLETS_PER_FIRE;
            this.decreaseBulletsInBarrel(BULLETS_PER_FIRE);
            if(this.getBulletsPerBarrel() <= 0 && this.getTotalBullets() >= this.getBarrelCapacity()){
                reload();
            }
        }
        result = 0;


        return result;
    }

}
