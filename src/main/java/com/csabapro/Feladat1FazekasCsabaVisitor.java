package com.csabapro;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.Token;

import com.csabapro.gen.ExprWithAssignBaseVisitor;
import com.csabapro.gen.ExprWithAssignParser;
import com.csabapro.gen.ExprWithAssignParser.AzonostoContext;
import com.csabapro.gen.ExprWithAssignParser.ErtekadasContext;
import com.csabapro.gen.ExprWithAssignParser.HatvanyozasContext;
import com.csabapro.gen.ExprWithAssignParser.KiirasContext;
import com.csabapro.gen.ExprWithAssignParser.OsszeadaskivonasContext;
import com.csabapro.gen.ExprWithAssignParser.SzamContext;
import com.csabapro.gen.ExprWithAssignParser.SzorzasosztasContext;
import com.csabapro.gen.ExprWithAssignParser.ZaroljelekContext;

public class Feladat1FazekasCsabaVisitor extends ExprWithAssignBaseVisitor<Integer> {
  Map<String, Integer> memory = new HashMap<>();

  @Override
  public Integer visitSzam(SzamContext ctx) {
    Integer value=Integer.valueOf(ctx.INT().getText());
    return value;
  }

  private Integer biexpression(Integer left, Integer right, Token jel) {
    Integer value;
    switch (jel.getType()) {
      case ExprWithAssignParser.ADD: value = left + right; break;
      case ExprWithAssignParser.SUB: value = left - right; break;
      case ExprWithAssignParser.MUL: value = left * right; break;
      case ExprWithAssignParser.DIV: value = left / right; break;
      case ExprWithAssignParser.EXP: value = Double.valueOf(Math.pow(left, right)).intValue(); break;
      default: throw new Error("Unexpected value");
    };
    return value;
  }

  @Override
  public Integer visitOsszeadaskivonas(OsszeadaskivonasContext ctx) {
    Integer left = visit(ctx.expr(0));
    Integer right = visit(ctx.expr(1));
    return biexpression(left, right, ctx.jel);
  }

  @Override
  public Integer visitSzorzasosztas(SzorzasosztasContext ctx) {
    Integer left = visit(ctx.expr(0));
    Integer right = visit(ctx.expr(1));
    return biexpression(left, right, ctx.jel);
  }

  @Override
  public Integer visitHatvanyozas(HatvanyozasContext ctx) {
    Integer left = visit(ctx.expr(0));
    Integer right = visit(ctx.expr(1));
    return biexpression(left, right, ctx.jel);
  }

  @Override
  public Integer visitErtekadas(ErtekadasContext ctx) {
    String id = ctx.ID().getText();
    Integer value = visit(ctx.expr());
    memory.put(id, value);
    return value;
  }

  @Override
  public Integer visitKiiras(KiirasContext ctx) {
    Integer value = visit(ctx.expr());
    System.out.println(value);
    return super.visitKiiras(ctx);
  }

  @Override
  public Integer visitZaroljelek(ZaroljelekContext ctx) {
    Integer value = visit(ctx.expr());
    return value;
  }

  @Override
  public Integer visitAzonosto(AzonostoContext ctx) {
    String id = ctx.ID().getText();
    Integer value = memory.getOrDefault(id, 0);
    return value;
  }
}
