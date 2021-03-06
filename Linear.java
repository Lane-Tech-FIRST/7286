
    package org.firstinspires.ftc.teamcode;

    import android.app.Activity;
    import android.graphics.Color;
    import android.view.View;

    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
    import com.qualcomm.robotcore.eventloop.opmode.Disabled;
    import com.qualcomm.robotcore.hardware.ColorSensor;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.qualcomm.robotcore.util.ElapsedTime;

    @TeleOp(name="Linear", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
    public class Linear extends LinearOpMode {
        //Declare the motors being used and a timer called "time"
        private ElapsedTime runtime = new ElapsedTime();
        DcMotor motor1;//Declare Motors for Drive Train
        DcMotor motor2;
        ColorSensor color;    // Hardware Device Object
        int count = 0;
        boolean sense = false;

        double currentTime = 0;
        ElapsedTime time;

        ElapsedTime colorTime;
        double colorTimer;

        //Gives the Enum State a name that we can use in the code to reference the 3 actions

        @Override
        public void runOpMode() throws InterruptedException {
            telemetry.addData("Status", "Initialized");
            telemetry.update();

            time = new ElapsedTime();
            colorTime = new ElapsedTime();

            motor1 = hardwareMap.dcMotor.get("motor_1");//rightwheels
            motor2 = hardwareMap.dcMotor.get("motor_2");

            motor2.setDirection(DcMotor.Direction.REVERSE);//reversed because the motors flipped

            color = hardwareMap.colorSensor.get("color sensor");
            color.enableLed(true); //*need    (TRUE)

            float hsvValues[] = {0F, 0F, 0F};

            final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);
            final float values[] = hsvValues;




            waitForStart();

            time.reset();
            colorTime.reset();


            // while the op mode is active, loop and read the RGB data.
            // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
            while (opModeIsActive()) {

                currentTime= time.time();

                Color.RGBToHSV(color.red() * 8, color.green() * 8, color.blue() * 8, hsvValues); //*need
                if (count == 0) {
                    time.reset();
                    count = 4;//time is zero
                }

                if (count == 1) {
                    motor1.setPower(1);
                    motor2.setPower(1);
                }

                if (count == 2) {
                    motor1.setPower(-1);
                    motor2.setPower(-1);

                }
                if(count == 4)
                {
                    motor1.setPower(.5);
                    motor2.setPower(.1);
                }

                if (count == 3)//color sensorruns// cant use counts in this section
                {
                    colorTime.reset();

                    colorTimer= colorTime.time();


                    if (color.red() > 16) ;//compare color.red()> threshold value
                    {
                       if ( && colorTime.time()<10)
                        {}
                    }

                    // else
                    {
                        //  hit other button)
                    }

                }


                if(currentTime>0 && !sense) {
                    count = 3;
                    sense = true;
                }


                    /*
                    if(time start<current time<time end)  //time end-time start = time runs
                    {
                    count = x;   //x corresponds to different actions
                    }
                     */
/*
                if (0 < currentTime && currentTime < .5)//turn right after moving forward
                {
                    count = 1;

                }
                if (.6 < currentTime && currentTime < .7)//turn right after moving forward
                {
                    count = 0;
                }
                if (.8 < currentTime && currentTime < 1.5)//turn right after moving forward
                {
                    count = 4;
                }
                if (1.6 < currentTime && currentTime < 1.7)//turn right after moving forward
                {
                    count = 0;
                }
                if (1.8 < currentTime && currentTime < 2.7)//turn right after moving forward
                {
                    count = 1;
                }
                if (2.7 < currentTime && currentTime < 2.8)//turn right after moving forward
                {
                    count = 0;
                }
                if (2.9< currentTime && currentTime <5//turn right after moving forward
                {
                    count = 3;
                }
                if (5.1 < currentTime && currentTime < 5.2)
                 {
                    count = 0;
                }
                if (5.3< currentTime && currentTime < 5.4)
                 {
                    count = 0;
                }
                if(5.5 < currentTime && currentTime < 17 )
*/
                telemetry.update();
                idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
            }

        }
    }

