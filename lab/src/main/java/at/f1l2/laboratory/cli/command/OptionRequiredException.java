package at.f1l2.laboratory.cli.command;

public class OptionRequiredException extends RuntimeException {

    private static final long serialVersionUID = 7489934064117893647L;

    public OptionRequiredException() {
        super("Option is required.");
    }

    public OptionRequiredException(String option) {
        super(String.format("Option '%s' is required.", option));
    }

}
