/**
 * @author Scott Carter
 */
package org.scarter4work.nickelplate.prompter;

/**
 * Enumerates the output message keys for the nickel plate program.
 */
public enum OutputEnum 
{
	SURFACE_AREA("ouptut.surfaceArea", new String [] {"%5.1f"}),
	AMPS_REQUIRED("output.ampsRequired", new String [] {"%5.2f"}),
	AMP_HOURS("output.ampHours", new String [] {"%5.2f"}),
	THICKNESS("output.thickness", new String [] {"%6.5f"}),
	SUMMARY("output.summary", new String [] {"%5.1f","%5.2f","%3.0f","%5.0f","%5.2f","%5.2f","%5.2f","%6.5f"}),
	THICK_ERROR("output.thickness.error", new String [] {});

	/** key */
	private String key;
	
	/** format values */
	private String [] formats;
	
	/** default constructor */
	private OutputEnum(String key, String [] formats)
	{
		this.key = key;
		this.formats = formats;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the formats
	 */
	public String[] getFormats() {
		return formats;
	}

	/**
	 * @param formats the formats to set
	 */
	public void setFormats(String[] formats) {
		this.formats = formats;
	}
}