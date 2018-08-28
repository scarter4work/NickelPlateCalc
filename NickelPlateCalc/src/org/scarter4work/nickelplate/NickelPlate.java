/**
 * @author Scott Carter (original program create date April 9, 1996)
 */
package org.scarter4work.nickelplate;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.scarter4work.nickelplate.calc.Calculator;
import org.scarter4work.nickelplate.prompter.ConsolePrompter;
import org.scarter4work.nickelplate.prompter.OutputEnum;
import org.scarter4work.nickelplate.prompter.PrompterOrderEnum;

/**
 * This program determines the thickness of a layer on nickel plating per side of a
 * workpiece using the dimensions of the workpiece and the before and after weights
 * of the workpiece.
 */
public class NickelPlate 
{
	/** the console */
	private Console console;
	
	/** console prompter */
	private ConsolePrompter prompter;
	
	/** calculator */
	private Calculator calculator;
	
	/** default constructor */
	public NickelPlate()
	{
		super();
		this.console = System.console();
	}
	
	/**
	 * Entry point to the application.  Makes the initial prompts to system out
	 * to collect workpiece dimensions and before and after weights.
	 * @param args - String [] of program arguments
	 * @throws Exception
	 */
	public static void main(String... args) throws Exception
	{
		// get a default properties filename if provided
		String filename = null;
		if (args.length > 0)
			filename = args[0];
		
		// create the instance of the nickel plate app + init
		NickelPlate app = new NickelPlate();
		app.setPrompter(new ConsolePrompter(filename));
		app.setCalculator(new Calculator());
		
		// create the exit flag
		String quit = "Y";
		
		do 
		{
			// iterate through the program prompts
			for (int i = 1; i < PrompterOrderEnum.values().length + 1; i++)
			{
				quit = app.processPrompt(i);
			}
			
		} while (!quit.equals("Y"));
	}

	/**
	 * Based on the prompt index passed in, the appropriate method is used to consume
	 * console input and set values on the calculator also causing calculations to 
	 * occur as necessary.
	 * @param index - int of the prompt to capture input for
	 * @return String of the quit values
	 */
	private String processPrompt(int index)
	{
		// set return value
		String output = "N";
		
		// get the current prompt
		String currentPrompt = this.getPrompter().getPrompt(index);
		console.printf("%s\t", currentPrompt);

		// switch on the index to call the appropriate handler method
		String answer = "";
		switch (index)
		{
			case 1:
				console.writer().println();
				console.writer().println();
				break;
			case 2:
				answer = console.readLine();
				this.calculator.setStartWeight(Double.valueOf(answer));
				console.writer().println();
				console.writer().println();
				break;
			case 3:
				answer = console.readLine();
				this.calculator.setWidth(Double.valueOf(answer));
				console.writer().println();
				console.writer().println();
				break;
			case 4: 
				answer = console.readLine();
				this.calculator.setLength(Double.valueOf(answer));
				console.writer().println();
				console.writer().println();
				break;
			case 5:
				answer = console.readLine();
				this.calculator.setNbrSidesPlated(Double.valueOf(answer));
				console.writer().println();
				console.writer().println();
				break;
			case 6:
				answer = console.readLine();
				this.calculator.setNbrPieces(Double.valueOf(answer));
				this.calculator.calculateSurfaceAreas();
				output = this.prompter.getOutput(OutputEnum.SURFACE_AREA, this.calculator.getTotalSurfaceArea());
				console.writer().print(output);
				console.writer().println();
				console.writer().println();
				break;
			case 7:
				answer = console.readLine();
				this.calculator.setSelection(Double.valueOf(answer));
				this.calculator.calculateCurrentValues();
				output = this.prompter.getOutput(OutputEnum.AMPS_REQUIRED, this.calculator.getTotalAmpsUsed());
				console.writer().print(output);
				console.writer().println();
				output = this.prompter.getOutput(OutputEnum.AMP_HOURS, this.calculator.getTotalAmpHoursUsed());
				console.writer().print(output);
				console.writer().println();
				console.writer().println();
				break;
			case 8:
				answer = console.readLine();
				this.calculator.setFinalWeight(Double.valueOf(answer));
				this.calculator.calculateNickelThickness();
				if (this.calculator.getThicknessPerSide() > 0.0002)
					output = this.prompter.getOutput(OutputEnum.THICK_ERROR, new Double(0));
				else
					output = this.prompter.getOutput(OutputEnum.THICKNESS, this.calculator.getThicknessPerSide());
				console.writer().print(output);
				console.writer().println();
				console.writer().println();

				List<Double> params = new ArrayList<>();
				params.add(this.calculator.getTotalAmpsUsed());
				params.add(this.calculator.getTotalAmpHoursUsed());
				params.add(this.calculator.getNbrSidesPlated());
				params.add(this.calculator.getNbrPieces());
				params.add(this.calculator.getWidth());
				params.add(this.calculator.getLength());
				params.add(this.calculator.getTotalSurfaceArea());
				params.add(this.calculator.getThicknessPerSide());
				output = this.prompter.getOutput(OutputEnum.SUMMARY, params.toArray(new Double[params.size()]));
				console.writer().println(output);
				console.writer().println();
				console.writer().println();

				break;
			case 9:
				answer = console.readLine();
				output = answer.toUpperCase().trim();
				break;
			default: output = "Y";
		}

		return output;
	}
	
	/**
	 * @return the prompter
	 */
	public ConsolePrompter getPrompter() {
		return prompter;
	}

	/**
	 * @param prompter the prompter to set
	 */
	public void setPrompter(ConsolePrompter prompter) {
		this.prompter = prompter;
	}

	/**
	 * @return the calculator
	 */
	public Calculator getCalculator() {
		return calculator;
	}

	/**
	 * @param calculator the calculator to set
	 */
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
}