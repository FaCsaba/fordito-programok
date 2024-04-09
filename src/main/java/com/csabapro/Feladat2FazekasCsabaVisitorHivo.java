package com.csabapro;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.*;

import com.csabapro.gen.LogExprLexer;
import com.csabapro.gen.LogExprParser;

//import java.util.List;

public class Feladat2FazekasCsabaVisitorHivo {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromFileName("t-log.expr");
        // System.out.println(input);

        LogExprLexer lexer = new LogExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // System.out.println(tokens.getNumberOfOnChannelTokens());
        // List<Token> alltokens = tokens.getTokens();
        // for (Token t : alltokens) System.out.println(t.toString());

        LogExprParser parser = new LogExprParser(tokens);
        ParseTree tree = parser.prog();
        System.out.println(tree.toStringTree(parser));

        // ParseTreeWalker walker = new ParseTreeWalker();
        // Walk the tree created during the parse, trigger callbacks
        Feladat2FazekasCsabaVisitor visitor = new Feladat2FazekasCsabaVisitor();

        visitor.visit(tree);
    }

}

