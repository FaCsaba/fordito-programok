package com.csabapro;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.antlr.v4.runtime.Token;

import com.csabapro.gen.ExprWithWhileParser;
import com.csabapro.gen.ExprWithWhileParser.AzonostoContext;
import com.csabapro.gen.ExprWithWhileParser.EgyenloContext;
import com.csabapro.gen.ExprWithWhileParser.ErtekadasContext;
import com.csabapro.gen.ExprWithWhileParser.HatvanyozasContext;
import com.csabapro.gen.ExprWithWhileParser.IfthenContext;
import com.csabapro.gen.ExprWithWhileParser.KiirasContext;
import com.csabapro.gen.ExprWithWhileParser.KisebbmintContext;
import com.csabapro.gen.ExprWithWhileParser.LogikaiszamContext;
import com.csabapro.gen.ExprWithWhileParser.ModuloContext;
import com.csabapro.gen.ExprWithWhileParser.NagyobbmintContext;
import com.csabapro.gen.ExprWithWhileParser.OsszeadaskivonasContext;
import com.csabapro.gen.ExprWithWhileParser.SzamContext;
import com.csabapro.gen.ExprWithWhileParser.SzorzasosztasContext;
import com.csabapro.gen.ExprWithWhileParser.WhiledoContext;
import com.csabapro.gen.ExprWithWhileParser.ZaroljelekContext;
import com.csabapro.gen.ExprWithWhileBaseVisitor;

public class ExprWithWhileVisitor extends ExprWithWhileBaseVisitor<Integer> {
  Map<String, Integer> memory = new HashMap<>();

  @Override
  public Integer visitSzam(SzamContext ctx) {
    Integer value = Integer.valueOf(ctx.INT().getText());
    return value;
  }

  @Override
  public Integer visitIfthen(IfthenContext ctx) {
    Integer value = visit(ctx.logexpr());
    if (value != 0) {
      return visit(ctx.stat());
    }

    return 0;
  }

  @Override
  public Integer visitWhiledo(WhiledoContext ctx) {
    while (visit(ctx.logexpr()) != 0) {
        visit(ctx.statlista());
    }
    return 0;
  }

  private Integer biexpression(Integer left, Integer right, Token jel) {
    Integer value;
    switch (jel.getType()) {
      case ExprWithWhileParser.ADD: value = left + right; break;
      case ExprWithWhileParser.SUB: value = left - right; break;
      case ExprWithWhileParser.MUL: value = left * right; break;
      case ExprWithWhileParser.DIV: value = left / right; break;
      case ExprWithWhileParser.EXP: value = (int) Math.pow(left, right); break;
      case ExprWithWhileParser.MOD: value = Math.floorMod(left, right); break;
      case ExprWithWhileParser.EQ : value = Objects.equals(left, right) ? 1 : 0; break;
      case ExprWithWhileParser.GT : value = left > right ? 1 : 0; break;
      case ExprWithWhileParser.LT : value = left < right ? 1 : 0; break;
      default: throw new Error("Unexpected value" + jel);
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
  public Integer visitModulo(ModuloContext ctx) {
    Integer left = visit(ctx.expr(0));
    Integer right = visit(ctx.expr(1));
    return biexpression(left, right, ctx.jel);
  }

  @Override
  public Integer visitNagyobbmint(NagyobbmintContext ctx) {
    Integer left = visit(ctx.expr(0));
    Integer right = visit(ctx.expr(1));
    return biexpression(left, right, ctx.jel);
  }

  @Override
  public Integer visitKisebbmint(KisebbmintContext ctx) {
    Integer left = visit(ctx.expr(0));
    Integer right = visit(ctx.expr(1));
    return biexpression(left, right, ctx.jel);
  }

  @Override
  public Integer visitEgyenlo(EgyenloContext ctx) {
    Integer left = visit(ctx.expr(0));
    Integer right = visit(ctx.expr(1));
    return biexpression(left, right, ctx.jel);
  }

  @Override
  public Integer visitLogikaiszam(LogikaiszamContext ctx) {
    return Integer.valueOf(ctx.INT().getText());
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
