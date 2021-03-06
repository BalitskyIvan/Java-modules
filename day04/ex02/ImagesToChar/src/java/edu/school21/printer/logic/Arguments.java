package edu.school21.printer.logic;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Arguments {
	@Parameter(names = {"--white", "-w"}, validateWith = ValidateParams.class)
	private String white;
	@Parameter(names = {"--black", "-b"}, validateWith = ValidateParams.class)
	private String black;

	public String getFirstParam() {
		return white;
	}
	public String getSecondParam() {
		return black;
	}
}
