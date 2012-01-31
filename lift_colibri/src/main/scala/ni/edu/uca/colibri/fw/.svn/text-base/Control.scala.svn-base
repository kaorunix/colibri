package ni.edu.uca.colibri.fw

case class Control {
	// Control type 0: Label 1:Text 2:... 
  /**
   * 0  NONE :No va aparecer
   * 1	LABEL
   * 2	TEXT
   * 3	PASS
   * 4	RADIO
   * 5	CHECKBOX
   * 6	RADIO2OP
   * 7	SELECT
   * 8	SELECT2OP
   * 9	SELECTLIST
   * 10	HIDDEN
   * 11	BUTTON
   */
    // Por defecto no va a aparecer
	var control = 0
	// Size of "<INPUT TYPE=TEXT SIZE=??"
	var size = 0
	// Size of "<INPUT TYPE=TEXT maxlength=??"
	var maxlength = 0
	def setControl(c: Int, s: Int, m: Int) {
		control = c
		size = s
		maxlength = m
	}
}
