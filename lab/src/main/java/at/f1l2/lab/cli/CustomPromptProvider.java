package at.f1l2.lab.cli;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("f1l2:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW).bold());
    }
}
