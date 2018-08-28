/**
 * @author Scott Carter
 */
package org.scarter4work.nickelplate.prompter;

/**
 * Enum supporting the console prompter with prompt names and order.
 * Index ordering is 1 based.
 */
public enum PrompterOrderEnum 
{
	PGM_DSC(1, "entry.programDescription.prompt"),
	INIT_WEIGHT(2, "entry.weight1.prompt"),
	WIDTH(3, "entry.width.prompt"),
	LENGTH(4, "entry.length.prompt"),
	NBR_SIDES(5, "entry.nbrSides.prompt"),
	NBR_PIECES(6, "entry.nbrPieces.prompt"),
	PANEL_SIZE(7, "entry.panelSize.prompt"),
	FINAL_WEIGHT(8, "entry.weight2.prompt"),
	QUIT(9, "entry.quit.prompt");

	/** index */
	private int index;
	/** key name */
	private String key;
	
	/** default constructor */
	private PrompterOrderEnum(int index, String key)
	{
		this.index = index;
		this.key = key;
	}
	
	/**
	 * Returns the key for the prompt index keyed in.
	 * @param index - index of the prompt to return
	 * @return String of the key for the given index
	 */
	public static String getKeyByIndex(int index)
	{
		for (PrompterOrderEnum po : PrompterOrderEnum.values())
		{
			if (po.getIndex() == index)
				return po.getKey();
		}
		return null;
	}
	
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
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
}