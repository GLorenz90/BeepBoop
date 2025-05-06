import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
//import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
//import lejos.hardware.port.SensorPort;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class BeepBoop {
		private enum States {
			WAIT, SEEK, MOVE, SPEAK
		}
		// Constants
		final double CENTER = 140;
		final double LEFT_DEADZONE = 20;
		final double RIGHT_DEADZONE = 20;
		final double ANGLE_FACTOR = 0.289;
		
		// EV3 Components
		private final EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.D);
		private final EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.A);
		private final Wheel rightTread = WheeledChassis.modelWheel(rightMotor, 43.5).offset(-80).invert(false);
		private final Wheel leftTread = WheeledChassis.modelWheel(leftMotor, 43.5).offset(80).invert(false);
		private final Chassis chassis = new WheeledChassis(new Wheel[] { rightTread, leftTread }, WheeledChassis.TYPE_DIFFERENTIAL);
		private final MovePilot pilot = new MovePilot(chassis);
		
		// Variables
		private States state = States.WAIT;
		private boolean isRunning = true;


		public BeepBoop(){
			pilot.setLinearSpeed(pilot.getMaxLinearSpeed());
			LCD.clearDisplay();
			LCD.drawString("Press center button to start.", 0, 1);
		}
		
		public void processStates() { // main loop
			while(isRunning) {
				
				
				if (state == States.WAIT) {
					performWait();
				}
				if (state == States.SEEK) {
					performSeek();
				}
				if (state == States.MOVE) {
					performMove();
				}
				if (state == States.SPEAK) {
					performSpeak();
				}
			}
		}
		
		public void performWait() { 
			// States -> Speak
			if(Button.ENTER.isDown()) {
				state = States.SPEAK;
			}
		}
		
		public void performSeek() {
			// States -> Move, Speak
			drawDebug();
		}
		
		public void performMove() {
			// States -> Seek, Speak
			drawDebug();
		}
		
		public void performSpeak() {
			// States -> Seek, Move
			drawDebug();
		}
		
		public void drawDebug() {
			LCD.clearDisplay();
			LCD.drawString("0123456789ABCDEF", 0, 0);
			LCD.drawString("STATE:", 1, 1);
			LCD.drawString(state.name(), 1, 8);
		}
}
