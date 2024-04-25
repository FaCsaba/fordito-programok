package com.csabapro;

import java.io.IOException;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import com.csabapro.gen.RomanLexer;
import com.csabapro.gen.RomanParser;

public class RomanVisitorHivo {
  public static void main(String[] args) throws IOException {
    CharStream input = CharStreams.fromFileName("t-roman.expr");
    
    RomanLexer lexer = new RomanLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    RomanParser parser = new RomanParser(tokens);
    ParseTree tree = parser.number();
    System.out.println(tree.toStringTree(parser));

    RomanVisitor visitor = new RomanVisitor();

    System.out.println(visitor.visit(tree));
  }
}
