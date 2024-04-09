package com.csabapro;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.*;

import com.csabapro.gen.ExprWithIfLexer;
import com.csabapro.gen.ExprWithIfParser;

public class ExprWithIfVisitorHivo {
      public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("t-if.expr");
        // System.out.println(input);

        ExprWithIfLexer lexer = new ExprWithIfLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // System.out.println(tokens.getNumberOfOnChannelTokens());
        // List<Token> alltokens = tokens.getTokens();
        // for (Token t : alltokens) System.out.println(t.toString());

        ExprWithIfParser parser = new ExprWithIfParser(tokens);
        ParseTree tree = parser.prog();
        System.out.println(tree.toStringTree(parser));

        // ParseTreeWalker walker = new ParseTreeWalker();
        // Walk the tree created during the parse, trigger callbacks
        ExprWithIfVisitor visitor = new ExprWithIfVisitor();

        visitor.visit(tree);
    }
}
