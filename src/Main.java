import java.util.Timer;


class  Whasher {
    private boolean spin;  // Отжим
    private String washingMode;    // режим, "Normal" - Normal Wash, "Rinsing" - Rinsing
    private boolean errorHandler; //true - если нет режима, стиральна я машина не запускается
    private boolean blockedWashHandler;
    private boolean blockedSpinHendler;
    Whasher(){
        this.spin = false;
        this.washingMode = "Normal";
        this.errorHandler = false;
        this.blockedWashHandler = false;
        this.blockedSpinHendler = false;
    }

     void toggleWashingMode(String mode) {  // выбор режима стирки
        if (blockedWashHandler == true){
            System.out.println("Blocked");
        }
        else{
        if (mode.equals("Normal") || mode.equals("Rinsing")){
            this.washingMode = mode;
            this.errorHandler = false;
        }
        else{
           System.out.println("ERROR! This mode don't exist");
           this.errorHandler = true;
        };
        };
    }

    void washing(boolean wash){  //кнопка стирка
        if (this.blockedWashHandler == true || blockedSpinHendler == true){
            System.out.println("Blocked");
        }
        else{
        this.blockedWashHandler = true;
        if (this.errorHandler == true){
            System.out.println("ERROR! Washing can't be started");
        }
        else {

            if (this.washingMode.equals("Normal")) {
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                System.out.println("Normal wash was ending");


                            }
                        },
                        300000
                );
            }
            ;

            if (this.washingMode.equals("Rinsing")) {
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                System.out.println("Rinsing was ending");

                            }
                        },
                        180000
                );
            }
            ;
        };
           this.blockedWashHandler = false;};
    }

    void spinMode() {   //кнопка отжим
        if (this.blockedWashHandler  == true || this.blockedSpinHendler == true){
            System.out.println("Blocked");
        }
        else {
            this.blockedSpinHendler =true;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("Spinning was ending");

                    }
                },
                180000
        );
            this.blockedSpinHendler = false;};
    }

}


public class Main {

    public static void main(String[] args) {
        Whasher whasher = new Whasher();
        System.out.println("1: Primary mode is normal");
        whasher.washing(true);

        System.out.println("2: mode is rinsing");
        whasher.toggleWashingMode("Rinsing");
        whasher.washing(true);

        System.out.println("3: mode is spinning");
        whasher.spinMode();




}
   }
