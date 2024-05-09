import java.awt.Color;
public class Steganography
{
    public static void main(String[] args)
    {
        //Notice how the picture needs to be created!
        //Don't change the String value in the Picture parameter.
        Picture beach = new Picture("beach_with_hidden_image.png");
        Picture flower = new Picture("flower1.jpg");
        Picture flower2 = new Picture("flower2.jpg");
        
        //System.out.println(view);
        //System.out.println(mount);

        //Picture revealed = revealPicture(flower2);
        //revealed.write("revealed.png");
        //I couldnt do this with my own pics so you let me submit with the flower pics
        System.out.println(canHide(flower2, flower));
        Picture newPic = hidePicture(flower2, flower);
        newPic.write("newPic.png");
//I couldnt do this with my own pics so you let me submit with the flower pics
        Picture exposed = revealPicture(newPic);
        exposed.write("exposed.png");

        Picture filterPic = filter(flower);
        filterPic.write("filterPic.png");
        }
    
    public static Picture filter(Picture secret){
        Picture hidden = new Picture(secret);
        Pixel[][] hidden_new = hidden.getPixels2D();

        for(int r = 0; r<hidden_new.length; r++){
            for (int c = 0; c<hidden_new[0].length; c++){
                Pixel x = hidden_new[r][c];
                Color x_col = x.getColor();

                int oldR_secret = x_col.getRed();
                int oldG_secret = x_col.getGreen();
                int oldB_secret = x_col.getBlue();

                int newR = oldR_secret * 100;
                int newG = oldG_secret * 1;
                int newB = oldB_secret * 1;

                x.setRed(newR);
                x.setBlue(newB);
                x.setGreen(newG);

            }
        }
        return hidden;
    }



    public static boolean canHide(Picture p, Picture s){
        if (p.getHeight() == s.getHeight() && p.getWidth() == s.getWidth()){
            return true;
          }  else{
            return false;
          }
    }
// creates new image
    public static Picture hidePicture(Picture source, Picture secret){
        Picture hidden = new Picture(secret);
        //finds the pixels
        Pixel[][] hidden_new = hidden.getPixels2D();
        Pixel[][] hidden_old = source.getPixels2D();
        //loops thru every pixel
        for(int r = 0; r < hidden_new.length; r++){
            for (int c=0; c<hidden_new[0].length; c++){
            //makes array with pixels
            Pixel x = hidden_new[r][c];
            Color x_col = x.getColor();

            Pixel x_old = hidden_old[r][c];
            Color c_old = x_old.getColor();
            //stores color of old image in variable
            int oldR = c_old.getRed();
            int oldG = c_old.getGreen();
            int oldB = c_old.getBlue();
            //stores color of image im hiding in a variable
            int oldR_secret = x_col.getRed();
            int oldG_secret = x_col.getGreen();
            int oldB_secret = x_col.getBlue();
            //takes the least signnificant bits
            int newR = oldR / 4 * 4;
            int newG = oldG / 4 * 4;
            int newB = oldB / 4 * 4;
            //getting only the most signifiant bits from the secret
            int newR_secret = oldR_secret / 64;
            int newG_secret = oldG_secret / 64;
            int newB_secret = oldB_secret / 64; 
            //combines the images
            newR = newR + newR_secret;
            newG = newG + newG_secret;
            newB = newB + newB_secret;
            //sets colors of new image
            x.setRed(newR);
            x.setGreen(newG);
            x.setBlue(newB);
        

            }
    }
    return hidden;
 }
    


    /**
    * Clear the lower (rightmost) two bits in a pixel.
    */
    public static void clearLow( Pixel p )
    {
        /* To be implemented */
    }


    public static Picture revealPicture(Picture p){
        Picture copy = new Picture(p);
        Pixel[][] pix_new = copy.getPixels2D();
        Pixel[][] pix_old = p.getPixels2D();

        for(int r = 0; r < pix_new.length; r++){
            for (int c=0; c<pix_new[0].length; c++){

            Pixel x = pix_new[r][c];

            Pixel x_old = pix_old[r][c];
            Color c_old = x_old.getColor();

            int oldR = c_old.getRed();
            int oldG = c_old.getGreen();
            int oldB = c_old.getBlue();

            int newR = oldR % 4 * 64;
            int newG = oldG % 4 * 64;
            int newB = oldB % 4 * 64;

            x.setRed(newR);
            x.setGreen(newG);
            x.setBlue(newB);

            
            }


        }
        return copy;
        
    }
    
     /**
     * Returns a new Picture object with the lowest two bits of each pixel cleared.
     */
    public static Picture testClearLow(Picture p)
    {
        /* To be implemented */
        Picture p2 = new Picture(p);
        return p2;
    }
    
}
