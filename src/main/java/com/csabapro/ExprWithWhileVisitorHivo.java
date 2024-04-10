package com.csabapro;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.csabapro.gen.ExprWithWhileLexer;
import com.csabapro.gen.ExprWithWhileParser;

public class ExprWithWhileVisitorHivo {
      public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("t-while.expr");
        // System.out.println(input);

        ExprWithWhileLexer lexer = new ExprWithWhileLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // System.out.println(tokens.getNumberOfOnChannelTokens());
        // List<Token> alltokens = tokens.getTokens();
        // for (Token t : alltokens) System.out.println(t.toString());

        ExprWithWhileParser parser = new ExprWithWhileParser(tokens);
        ParseTree tree = parser.prog();
        System.out.println(tree.toStringTree(parser));

        // ParseTreeWalker walker = new ParseTreeWalker();
        // Walk the tree created during the parse, trigger callbacks
        ExprWithWhileVisitor visitor = new ExprWithWhileVisitor();

        visitor.visit(tree);
    }}
