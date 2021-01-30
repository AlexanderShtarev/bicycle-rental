package com.epam.jwd;

import com.epam.jwd.domain.Product;
import com.epam.jwd.validator.AnnotationValidator;
import com.epam.jwd.validator.rule.LengthRule;
import com.epam.jwd.validator.rule.NotEmptyRule;
import com.epam.jwd.validator.rule.Rule;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

            NotEmptyRule notEmptyRule = new NotEmptyRule();
            LengthRule lengthRule = new LengthRule();
            List<Rule<?>> rules = new ArrayList<>() {{
                add(notEmptyRule);
                add(lengthRule);
            }};
            Product test = Product.builder().model("").build();
            AnnotationValidator annotationValidator
                    = new AnnotationValidator(rules);
            annotationValidator.validate(test);

    }

}
