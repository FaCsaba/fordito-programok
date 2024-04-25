generate:
antlr4 ./grammars/Expr.g4 -visitor -o .\src\main\java\com\csabapro\gen -package com.csabapro.gen
antlr4 ./grammars/ExprWithAssign.g4 -visitor -o .\src\main\java\com\csabapro\gen -package com.csabapro.gen
antlr4 ./grammars/ExprWithIf.g4 -visitor -o .\src\main\java\com\csabapro\gen -package com.csabapro.gen
antlr4 ./grammars/LabeledExpr.g4 -visitor -o .\src\main\java\com\csabapro\gen -package com.csabapro.gen
antlr4 ./grammars/LogExpr.g4 -visitor -o .\src\main\java\com\csabapro\gen -package com.csabapro.gen
antlr4 ./grammars/ExprWithWhile.g4 -visitor -o .\src\main\java\com\csabapro\gen -package com.csabapro.gen
antlr4 ./grammars/Roman.g4 -visitor -o .\src\main\java\com\csabapro\gen -package com.csabapro.gen