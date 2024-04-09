package com.csabapro;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.*;

import com.csabapro.gen.ExprWithAssignLexer;
import com.csabapro.gen.ExprWithAssignParser;

//import java.util.List;

public class Feladat1FazekasCsabaVisitorHivo {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("t.expr");
        // System.out.println(input);

        ExprWithAssignLexer lexer = new ExprWithAssignLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // System.out.println(tokens.getNumberOfOnChannelTokens());
        // List<Token> alltokens = tokens.getTokens();
        // for (Token t : alltokens) System.out.println(t.toString());

        ExprWithAssignParser parser = new ExprWithAssignParser(tokens);
        ParseTree tree = parser.prog();
        System.out.println(tree.toStringTree(parser));

        // ParseTreeWalker walker = new ParseTreeWalker();
        // Walk the tree created during the parse, trigger callbacks
        Feladat1FazekasCsabaVisitor visitor = new Feladat1FazekasCsabaVisitor();

        visitor.visit(tree);
    }

}

