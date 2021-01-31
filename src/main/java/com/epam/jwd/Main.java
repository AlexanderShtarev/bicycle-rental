package com.epam.jwd;

import com.epam.jwd.domain.User;
import com.epam.jwd.domain.UserRole;
import com.epam.jwd.domain.UserStatus;
import com.epam.jwd.validator.AnnotationValidator;
import com.epam.jwd.validator.rule.LengthRule;
import com.epam.jwd.validator.rule.NotEmptyRule;
import com.epam.jwd.validator.rule.PatternRule;
import com.epam.jwd.validator.rule.Rule;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        User criteria = User.builder()
                .name("Alex")
                .email("as")
                .balance(0d)
                .password("123")
                .status(UserStatus.NON_ACTIVE)
                .build();
        String table = "users";
        criteria.setRoles(new ArrayList<>() {{add(UserRole.CLIENT); add(UserRole.ADMIN);}});

        List<String> columnNames = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        /*for (Field f : User.class.getDeclaredFields()) {
            Column column = f.getAnnotation(Column.class);
             if (column != null) {
                f.setAccessible(true);
                columnNames.add(column.name());
                try {
                    values.add(f.get(user));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }*/



            NotEmptyRule notEmptyRule = new NotEmptyRule();
            LengthRule lengthRule = new LengthRule();
            PatternRule patternRule = new PatternRule();
            List<Rule<?>> rules = new ArrayList<>() {{
                add(notEmptyRule);
                add(lengthRule);
                add(patternRule);
            }};
            AnnotationValidator annotationValidator
                    = new AnnotationValidator(rules);
            annotationValidator.validate(criteria);

    }

}
