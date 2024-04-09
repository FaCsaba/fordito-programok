package com.csabapro;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.antlr.v4.runtime.Token;

import com.csabapro.gen.ExprWithIfParser;
import com.csabapro.gen.ExprWithIfParser.AzonostoContext;
import com.csabapro.gen.ExprWithIfParser.EgyenloContext;
import com.csabapro.gen.ExprWithIfParser.ErtekadasContext;
import com.csabapro.gen.ExprWithIfParser.HatvanyozasContext;
import com.csabapro.gen.ExprWithIfParser.IfthenContext;
import com.csabapro.gen.ExprWithIfParser.KiirasContext;
import com.csabapro.gen.ExprWithIfParser.KisebbmintContext;
import com.csabapro.gen.ExprWithIfParser.LogikaiszamContext;
import com.csabapro.gen.ExprWithIfParser.ModuloContext;
import com.csabapro.gen.ExprWithIfParser.NagyobbmintContext;
import com.csabapro.gen.ExprWithIfParser.OsszeadaskivonasContext;
import com.csabapro.gen.ExprWithIfParser.SzorzasosztasContext;
import com.csabapro.gen.ExprWithIfParser.WhiledoContext;
import com.csabapro.gen.ExprWithIfParser.ZaroljelekContext;
import com.csabapro.gen.ExprWithIfBaseVisitor;
import com.csabapro.gen.ExprWithIfParser.SzamContext;

public class ExprWithIfVisitor extends ExprWithIfBaseVisitor<Integer> {
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
      case ExprWithIfParser.ADD: value = left + right; break;
      case ExprWithIfParser.SUB: value = left - right; break;
      case ExprWithIfParser.MUL: value = left * right; break;
      case ExprWithIfParser.DIV: value = left / right; break;
      case ExprWithIfParser.EXP: value = (int) Math.pow(left, right); break;
      case ExprWithIfParser.MOD: value = left % right; break;
      case ExprWithIfParser.EQ : value = Objects.equals(left, right) ? 1 : 0; break;
      case ExprWithIfParser.GT : value = left > right ? 1 : 0; break;
      case ExprWithIfParser.LT : value = left < right ? 1 : 0; break;
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
