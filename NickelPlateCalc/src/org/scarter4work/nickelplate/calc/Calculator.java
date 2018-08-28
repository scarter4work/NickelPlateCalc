/**
 * @author Scott Carter
 */
package org.scarter4work.nickelplate.calc;

/**
 * This class computes all the necessary values to provide answers
 * for the final report for the nickel plate thickness program.
 */
public class Calculator 
{
	/** amp factor */
	private static final Double AMP_FACTOR = new Double(0.03855);
	/** minutes per hour */
	private static final Integer MIN_PER_HOUR = new Integer(60);
	/** amp hour factor */
	private static final Double AMP_HOUR_FACTOR = new Double(2.203);
	/** thickness factor */
	private static final Integer THICKNESS_FACTOR = new Integer(150);
	
	/** start weight of the work piece */
	private Double startWeight;
	/** final weight of the work piece */
	private Double finalWeight;
	/** amps used */
	private Double ampsUsed;
	/** total number of amps used for plating */
	private Double totalAmpsUsed;
	/** number of amp hours used for plating */
	private Double ampHoursUsed;
	/** total number of amp hours used */
	private Double totalAmpHoursUsed;
	/** number of sides plated */
	private Double nbrSidesPlated;
	/** number of pieces plated */
	private Double nbrPieces;
	/** width of the work piece */
	private Double width;
	/**	length of the work piece */
	private Double length;
	/** thickness of nickel per side of the work piece */
	private Double thicknessPerSide;
	/** difference between measurements */
	private Double delta;
	/** the total surface area of all work pieces */
	private Double totalSurfaceArea;
	/** total surface area of a work piece */
	private Double surfaceArea;
	/** selection value - coupon or panel */
	private Double selection;
	
	/** default constructor */
	public Calculator()
	{
		super();
		this.initCalculatorValues();
	}

	/**
	 * Calculates the surface area and total surface area given 
	 * the length, width, and number of pieces used.
	 */
	public void calculateSurfaceAreas()
	{
		this.surfaceArea = this.length.doubleValue() * this.width.doubleValue() * this.nbrSidesPlated.doubleValue();
		this.totalSurfaceArea = this.surfaceArea.doubleValue() * this.nbrPieces.doubleValue();
	}
	
	/**
	 * Calculates the current values for amp hours and amps
	 * used both in flight values and total values.
	 */
	public void calculateCurrentValues()
	{
		this.ampsUsed = this.surfaceArea.doubleValue() * AMP_FACTOR * this.selection.doubleValue();
		this.ampHoursUsed = (this.surfaceArea.doubleValue() * AMP_HOUR_FACTOR)/MIN_PER_HOUR;
		this.totalAmpsUsed = this.nbrPieces.doubleValue() * this.ampsUsed.doubleValue();
		this.totalAmpHoursUsed = this.nbrPieces.doubleValue() * this.ampHoursUsed.doubleValue();
	}
	
	/**
	 * Calculates the nickel thickness values.
	 */
	public void calculateNickelThickness()
	{
		this.delta = this.finalWeight.doubleValue() - this.startWeight.doubleValue();
		this.thicknessPerSide = 
				((this.delta.doubleValue()/(this.totalSurfaceArea.doubleValue()/this.nbrPieces.doubleValue())))/THICKNESS_FACTOR;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Calculator [startWeight=" + startWeight + ", finalWeight="
				+ finalWeight + ", ampsUsed=" + ampsUsed + ", totalAmpsUsed="
				+ totalAmpsUsed + ", ampHoursUsed=" + ampHoursUsed
				+ ", totalAmpHoursUsed=" + totalAmpHoursUsed
				+ ", nbrSidesPlated=" + nbrSidesPlated + ", nbrPieces="
				+ nbrPieces + ", width=" + width + ", length=" + length
				+ ", thicknessPerSide=" + thicknessPerSide + ", delta=" + delta
				+ ", totalSurfaceArea=" + totalSurfaceArea + ", surfaceArea="
				+ surfaceArea + ", selection=" + selection + "]";
	}

	/**
	 * Initializes all calculated values.
	 */
	private void initCalculatorValues()
	{
		this.startWeight = Double.valueOf(0);
		this.finalWeight = Double.valueOf(0);
		this.ampsUsed = Double.valueOf(0);
		this.ampHoursUsed = Double.valueOf(0);
		this.nbrSidesPlated = Double.valueOf(0);
		this.nbrPieces = Double.valueOf(0);
		this.width = Double.valueOf(0);
		this.length = Double.valueOf(0);
		this.thicknessPerSide = Double.valueOf(0);
		this.delta = Double.valueOf(0);
		this.totalSurfaceArea = Double.valueOf(0);
		this.surfaceArea = Double.valueOf(0);
	}

	/**
	 * @return the startWeight
	 */
	public Double getStartWeight() {
		return startWeight;
	}

	/**
	 * @param startWeight the startWeight to set
	 */
	public void setStartWeight(Double startWeight) {
		this.startWeight = startWeight;
	}

	/**
	 * @return the finalWeight
	 */
	public Double getFinalWeight() {
		return finalWeight;
	}

	/**
	 * @param finalWeight the finalWeight to set
	 */
	public void setFinalWeight(Double finalWeight) {
		this.finalWeight = finalWeight;
	}

	/**
	 * @return the ampsUsed
	 */
	public Double getAmpsUsed() {
		return ampsUsed;
	}

	/**
	 * @param ampsUsed the ampsUsed to set
	 */
	public void setAmpsUsed(Double ampsUsed) {
		this.ampsUsed = ampsUsed;
	}

	/**
	 * @return the totalAmpsUsed
	 */
	public Double getTotalAmpsUsed() {
		return totalAmpsUsed;
	}

	/**
	 * @param totalAmpsUsed the totalAmpsUsed to set
	 */
	public void setTotalAmpsUsed(Double totalAmpsUsed) {
		this.totalAmpsUsed = totalAmpsUsed;
	}

	/**
	 * @return the ampHoursUsed
	 */
	public Double getAmpHoursUsed() {
		return ampHoursUsed;
	}

	/**
	 * @param ampHoursUsed the ampHoursUsed to set
	 */
	public void setAmpHoursUsed(Double ampHoursUsed) {
		this.ampHoursUsed = ampHoursUsed;
	}

	/**
	 * @return the totalAmpHoursUsed
	 */
	public Double getTotalAmpHoursUsed() {
		return totalAmpHoursUsed;
	}

	/**
	 * @param totalAmpHoursUsed the totalAmpHoursUsed to set
	 */
	public void setTotalAmpHoursUsed(Double totalAmpHoursUsed) {
		this.totalAmpHoursUsed = totalAmpHoursUsed;
	}

	/**
	 * @return the nbrSidesPlated
	 */
	public Double getNbrSidesPlated() {
		return nbrSidesPlated;
	}

	/**
	 * @param nbrSidesPlated the nbrSidesPlated to set
	 */
	public void setNbrSidesPlated(Double nbrSidesPlated) {
		this.nbrSidesPlated = nbrSidesPlated;
	}

	/**
	 * @return the nbrPieces
	 */
	public Double getNbrPieces() {
		return nbrPieces;
	}

	/**
	 * @param nbrPieces the nbrPieces to set
	 */
	public void setNbrPieces(Double nbrPieces) {
		this.nbrPieces = nbrPieces;
	}

	/**
	 * @return the width
	 */
	public Double getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Double width) {
		this.width = width;
	}

	/**
	 * @return the length
	 */
	public Double getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(Double length) {
		this.length = length;
	}

	/**
	 * @return the thicknessPerSide
	 */
	public Double getThicknessPerSide() {
		return thicknessPerSide;
	}

	/**
	 * @param thicknessPerSide the thicknessPerSide to set
	 */
	public void setThicknessPerSide(Double thicknessPerSide) {
		this.thicknessPerSide = thicknessPerSide;
	}

	/**
	 * @return the delta
	 */
	public Double getDelta() {
		return delta;
	}

	/**
	 * @param delta the delta to set
	 */
	public void setDelta(Double delta) {
		this.delta = delta;
	}

	/**
	 * @return the totalSurfaceArea
	 */
	public Double getTotalSurfaceArea() {
		return totalSurfaceArea;
	}

	/**
	 * @param totalSurfaceArea the totalSurfaceArea to set
	 */
	public void setTotalSurfaceArea(Double totalSurfaceArea) {
		this.totalSurfaceArea = totalSurfaceArea;
	}

	/**
	 * @return the surfaceArea
	 */
	public Double getSurfaceArea() {
		return surfaceArea;
	}

	/**
	 * @param surfaceArea the surfaceArea to set
	 */
	public void setSurfaceArea(Double surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	/**
	 * @return the selection
	 */
	public Double getSelection() {
		return selection;
	}

	/**
	 * @param selection the selection to set
	 */
	public void setSelection(Double selection) {
		this.selection = selection;
	}
}