package com.csabapro;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.csabapro.gen.LogExprBaseVisitor;
import com.csabapro.gen.LogExprParser;
import com.csabapro.gen.LogExprParser.AndContext;
import com.csabapro.gen.LogExprParser.AssignContext;
import com.csabapro.gen.LogExprParser.BoolContext;
import com.csabapro.gen.LogExprParser.IdContext;
import com.csabapro.gen.LogExprParser.ImpContext;
import com.csabapro.gen.LogExprParser.OrContext;
import com.csabapro.gen.LogExprParser.ParenContext;
import com.csabapro.gen.LogExprParser.PrintContext;

public class Feladat2FazekasCsabaVisitor extends LogExprBaseVisitor<Boolean> {
  Map<String, Boolean> memory = new HashMap<>();

  @Override
  public Boolean visitParen(ParenContext ctx) {
    Boolean value = visit(ctx.expr());
    return value;
  }

  @Override
  public Boolean visitBool(BoolContext ctx) {
    Boolean value = Objects.equals(ctx.BOOL().getSymbol().getText(), "1");
    return value;
  }

  @Override
  public Boolean visitId(IdContext ctx) {
    return memory.getOrDefault(ctx.ID().getText(), false);
  }

  @Override
  public Boolean visitImp(ImpContext ctx) {
    return !visit(ctx.expr(0)) || visit(ctx.expr(1));
  }

  @Override
  public Boolean visitOr(OrContext ctx) {
    return visit(ctx.expr(0)) || visit(ctx.expr(1));
  }

  @Override
  public Boolean visitAnd(AndContext ctx) {
    return visit(ctx.expr(0)) && visit(ctx.expr(1));
  }

  @Override
  public Boolean visitAssign(AssignContext ctx) {
    Boolean value = visit(ctx.expr());
    String id = ctx.ID().getText();
    memory.put(id, value);
    return super.visitAssign(ctx);
  }

  @Override
  public Boolean visitPrint(PrintContext ctx) {
    Boolean value = visit(ctx.expr());
    System.out.println(value);
    return value;
  }
}
