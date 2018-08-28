/**
 * @author Scott Carter (original code written on April 9, 1996)
 */
package org.scarter4work.nickelplate.prompter;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * This class is responsible for delivering all the console prompts for the 
 * nickel plate application.
 */
public class ConsolePrompter 
{
	/** property file name */
	private String filename = "./nickelplate_text.properties";
	
	/** properties for prompt text */
	Map<String,String> prompts = new HashMap<String,String>();
	
	/** default constructor */
	public ConsolePrompter(String filename)
	{
		super();
		// set filename override only if set
		if (filename != null && filename.length() > 0)
			this.filename = filename;
		this.initializeProperties();
	}
	
	/**
	 * Returns the prompt from the properties file for the given index value.
	 * Using the index, it retrieves the corresponding key from the PrompterOrderEnum
	 * to fetch the correct value from the properties file.
	 * @param index - 1 based index of the prompt to retrieve
	 * @return String of the value of the prompt
	 */
	public String getPrompt(int index)
	{
		// verify that we have a properties object on this class
		if (this.prompts == null) this.initializeProperties();
		
		// look up the key for the index using the enum
		String key = PrompterOrderEnum.getKeyByIndex(index);
		
		// get the value from properties using the key
		String value = this.prompts.get(key);
		
		return value;
	}
	
	/**
	 * Given the output enumeration passed in, we get the output
	 * text from the hashmap using the key of the output enum. 
	 * Then we perform any string replacements with formatting
	 * required, and return the final output to the console.
	 * @param outputEnum - OutputEnum
	 * @param params - List<Double> of calculated answers to replace into the output
	 * @return String of the formatted output ready to display to the console
	 */
	public String getOutput(OutputEnum outputEnum, Double... params)
	{
		String retVal = "";
		
		// verify that the formats match the number of params
		if (outputEnum.getFormats().length != params.length)
			return retVal;
		
		// get the raw output value from the hashmap
		String value = this.prompts.get(outputEnum.getKey());
		
		// for each value of the passed in params array
		for (int i = 0; i < params.length; i++)
		{
			String format = outputEnum.getFormats()[i];
			Double param = params[i];
			
			// format the param into a string
			String subValue = String.format(format, param);
			
			// replace the formated value into the raw output
			value = value.replaceFirst("(\\{.*?\\})", subValue);
		}

		// set the return value to the value
		retVal = value;
		
		return retVal;
	}
	
	/**
	 * Initializes the properties file to get prompts for the app from.
	 */
	private void initializeProperties()
	{
		try
		{
			Properties properties = new Properties();
			properties.load(new FileInputStream(this.filename));
			
			for (Map.Entry<?,?> entry : properties.entrySet())
			{
				this.prompts.put((String)entry.getKey(), (String)entry.getValue());
			}
		}
		catch (Exception ex)
		{
			throw new RuntimeException("No entries were found or properties file could not be loaded.", ex);
		}
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
}