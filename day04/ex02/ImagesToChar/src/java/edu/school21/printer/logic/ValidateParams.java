package edu.school21.printer.logic;
import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import com.diogonunes.jcdp.color.api.Ansi;

public class ValidateParams implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
        for (Ansi.BColor color : Ansi.BColor.values()) {
            if (color.name().equals(value))
                return;
        }
        throw new ParameterException("No such color " + name);
    }
}
