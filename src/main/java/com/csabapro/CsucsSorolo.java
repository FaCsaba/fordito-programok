package com.csabapro;

import org.antlr.v4.runtime.ParserRuleContext;

import com.csabapro.gen.ExprBaseListener;

public class CsucsSorolo extends ExprBaseListener {
    int i = 0;

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        i = i + 1;
        System.out.println(ctx.depth());
        System.out.println("Mélység: " + i);
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        i = i - 1;
        System.out.println("Mélység kilépése ebbe: "+i);
    }
}
