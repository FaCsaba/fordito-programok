package com.csabapro;

import com.csabapro.gen.RomanBaseVisitor;
import com.csabapro.gen.RomanParser.HundredsContext;
import com.csabapro.gen.RomanParser.TensContext;
import com.csabapro.gen.RomanParser.ThousandsContext;
import com.csabapro.gen.RomanParser.UnitsContext;

public class RomanVisitor extends RomanBaseVisitor<Integer> {
  private Integer result = 0;

  @Override
  public Integer visitUnits(UnitsContext ctx) {
    if (ctx.NINE() != null) {
      result += 9;
    } else if (ctx.FOUR() != null) {
      result += 4;
    } else if (ctx.FIVE() != null) {
      result += 5 + ctx.ONE().size();
    } else {
      result += ctx.ONE().size();
    }
    return result;
  }

  @Override
  public Integer visitTens(TensContext ctx) {
    if (ctx.NINETY() != null) {
      result += 90;
    } else if (ctx.FORTY() != null) {
      result += 40;
    } else if (ctx.FIFTY() != null) {
      result += 50 + ctx.TEN().size() * 10;
    } else {
      result += ctx.TEN().size() * 10;
    }
    return result;
  }

  @Override
  public Integer visitHundreds(HundredsContext ctx) {
    if (ctx.NINEHUNDRED() != null) {
      result += 900;
    } else if (ctx.FOURHUNDRED() != null) {
      result += 400;
    } else if (ctx.FIVEHUNDRED() != null) {
      result += 500 + ctx.HUNDRED().size() * 100;
    } else {
      result += ctx.HUNDRED().size() * 100;
    }
    return result;
  }

  @Override
  public Integer visitThousands(ThousandsContext ctx) {
    result += ctx.THOUSAND().size() * 1000;
    return result;
  }
}
