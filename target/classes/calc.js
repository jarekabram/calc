if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'calc'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'calc'.");
}
var calc = function (_, Kotlin) {
  'use strict';
  var iterator = Kotlin.kotlin.text.iterator_gw00vp$;
  var CharRange = Kotlin.kotlin.ranges.CharRange;
  var lastOrNull = Kotlin.kotlin.text.lastOrNull_gw00vp$;
  var toBoxedChar = Kotlin.toBoxedChar;
  var unboxChar = Kotlin.unboxChar;
  var equals = Kotlin.equals;
  var Unit = Kotlin.kotlin.Unit;
  var toDouble = Kotlin.kotlin.text.toDouble_pdl1vz$;
  var ensureNotNull = Kotlin.ensureNotNull;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var toString = Kotlin.toString;
  var throwCCE = Kotlin.throwCCE;
  var lastOrNull_0 = Kotlin.kotlin.collections.lastOrNull_2p1efm$;
  var get_lastIndex = Kotlin.kotlin.collections.get_lastIndex_55thoc$;
  var IndexOutOfBoundsException = Kotlin.kotlin.IndexOutOfBoundsException;
  var MutableIterator = Kotlin.kotlin.collections.MutableIterator;
  var MutableIterable = Kotlin.kotlin.collections.MutableIterable;
  function Calculator() {
  }
  Calculator.prototype.run_61zpoe$ = function (input) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    var operators = new Stack();
    var output = '';
    var lastCharacter = '';
    tmp$ = iterator(input);
    while (tmp$.hasNext()) {
      var character = unboxChar(tmp$.next());
      if (character === 40) {
        var $receiver = new CharRange(48, 57);
        var element = lastOrNull(lastCharacter);
        if (element != null && $receiver.contains_mef7kx$(element)) {
          output += ' ';
        }
        operators.push_11rb$(toBoxedChar(character));
      }
       else if (character === 41) {
        var $receiver_0 = new CharRange(48, 57);
        var element_0 = lastOrNull(lastCharacter);
        if (element_0 != null && $receiver_0.contains_mef7kx$(element_0)) {
          output += ' ';
        }
        while (unboxChar(operators.peek()) !== 40) {
          output += ((tmp$_0 = unboxChar(operators.peek())) === null ? null : String.fromCharCode(tmp$_0)) + ' ';
          operators.pop();
        }
        operators.pop();
      }
       else if (this.isOperator_84ktsb$_0(character)) {
        var $receiver_1 = new CharRange(48, 57);
        var element_1 = lastOrNull(lastCharacter);
        if (element_1 != null && $receiver_1.contains_mef7kx$(element_1)) {
          output += ' ';
        }
        if (operators.isEmpty() || this.priority_a88kp8$_0(character) > this.priority_a88kp8$_0(unboxChar(operators.peek()))) {
          operators.push_11rb$(toBoxedChar(character));
        }
         else {
          while (this.priority_a88kp8$_0(unboxChar(operators.peek())) >= this.priority_a88kp8$_0(character)) {
            output += ((tmp$_1 = unboxChar(operators.peek())) === null ? null : String.fromCharCode(tmp$_1)) + ' ';
            operators.pop();
          }
          operators.push_11rb$(toBoxedChar(character));
        }
      }
       else {
        var tmp$_3 = (new CharRange(48, 57)).contains_mef7kx$(character);
        if (tmp$_3) {
          var $receiver_2 = new CharRange(48, 57);
          var element_2 = lastOrNull(lastCharacter);
          tmp$_3 = element_2 != null && $receiver_2.contains_mef7kx$(element_2) || equals(lastCharacter, '');
        }
        if (tmp$_3) {
          output += String.fromCharCode(character);
        }
         else {
          output += String.fromCharCode(character) + ' ';
        }
      }
      lastCharacter += String.fromCharCode(character);
    }
    while (!operators.isEmpty()) {
      output += ((tmp$_2 = unboxChar(operators.peek())) === null ? null : String.fromCharCode(tmp$_2)) + ' ';
      operators.pop();
    }
    return output;
  };
  Calculator.prototype.calculate_61zpoe$ = function (input) {
    var tmp$;
    var stackDouble = new Stack();
    var result = 0.0;
    var temp = '';
    tmp$ = iterator(input);
    while (tmp$.hasNext()) {
      var character = unboxChar(tmp$.next());
      if (this.isOperator_84ktsb$_0(character)) {
        var $receiver = new CharRange(48, 57);
        var element = lastOrNull(temp);
        if (element != null && $receiver.contains_mef7kx$(element)) {
          stackDouble.push_11rb$(toDouble(temp));
        }
        result = this.doEquation_dyvgo2$_0(stackDouble, character);
      }
       else if (character === 32) {
        var $receiver_0 = new CharRange(48, 57);
        var element_0 = lastOrNull(temp);
        if (element_0 != null && $receiver_0.contains_mef7kx$(element_0)) {
          stackDouble.push_11rb$(toDouble(temp));
        }
      }
       else {
        temp += String.fromCharCode(character);
      }
    }
    return result;
  };
  var Math_0 = Math;
  Calculator.prototype.doEquation_dyvgo2$_0 = function (stack, c) {
    var temp = 0.0;
    var d;
    switch (c) {
      case 45:
        temp = ensureNotNull(stack.peek());
        stack.pop();
        d = ensureNotNull(stack.peek());
        temp -= d - temp;
        stack.replaceTop_11rb$(temp);
        break;
      case 43:
        temp = ensureNotNull(stack.peek());
        stack.pop();
        d = ensureNotNull(stack.peek());
        temp = d + temp;
        stack.replaceTop_11rb$(temp);
        break;
      case 42:
        temp = ensureNotNull(stack.peek());
        stack.pop();
        d = ensureNotNull(stack.peek());
        temp = d * temp;
        stack.replaceTop_11rb$(temp);
        break;
      case 47:
        temp = ensureNotNull(stack.peek());
        stack.pop();
        d = ensureNotNull(stack.peek());
        temp = d / temp;
        stack.replaceTop_11rb$(temp);
        break;
      case 37:
        temp = ensureNotNull(stack.peek());
        stack.pop();
        d = ensureNotNull(stack.peek());
        temp = d % temp;
        stack.replaceTop_11rb$(temp);
        break;
      case 94:
        temp = ensureNotNull(stack.peek());
        stack.pop();
        d = ensureNotNull(stack.peek());
        var $receiver = d;
        var x = temp;
        temp = Math_0.pow($receiver, x);
        stack.replaceTop_11rb$(temp);
        break;
    }
    return temp;
  };
  Calculator.prototype.priority_a88kp8$_0 = function (peek) {
    var tmp$;
    switch (peek) {
      case 94:
        tmp$ = 3;
        break;
      case 42:
      case 47:
        tmp$ = 2;
        break;
      case 43:
      case 45:
      case 41:
        tmp$ = 1;
        break;
      default:tmp$ = 0;
        break;
    }
    return tmp$;
  };
  Calculator.prototype.isOperator_84ktsb$_0 = function (character) {
    var tmp$;
    switch (character) {
      case 45:
      case 43:
      case 42:
      case 47:
      case 37:
      case 94:
        tmp$ = true;
        break;
      default:tmp$ = false;
        break;
    }
    return tmp$;
  };
  Calculator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Calculator',
    interfaces: []
  };
  function main(args) {
    var htmlController = new HTMLController();
    htmlController.onClick();
  }
  function HTMLController() {
    var tmp$, tmp$_0;
    this.insertTextLabel_0 = Kotlin.isType(tmp$ = document.getElementById('insert_text_label'), HTMLInputElement) ? tmp$ : throwCCE();
    this.doEquationButton_0 = Kotlin.isType(tmp$_0 = document.getElementById('do_equation_button'), HTMLButtonElement) ? tmp$_0 : throwCCE();
    this.equation_0 = '';
  }
  function HTMLController$onClick$lambda(this$HTMLController) {
    return function (it) {
      this$HTMLController.equation_0 = this$HTMLController.insertTextLabel_0.value;
      if (this$HTMLController.isStringAnEquation_0(this$HTMLController.equation_0)) {
        var calc = new Calculator();
        var str = calc.run_61zpoe$(this$HTMLController.equation_0);
        window.alert('Equation in onp: ' + str);
        var result = calc.calculate_61zpoe$(str);
        window.alert('Equation result: ' + toString(result));
      }
       else {
        window.alert('Introduced string is not an equation');
      }
      return Unit;
    };
  }
  HTMLController.prototype.onClick = function () {
    this.doEquationButton_0.addEventListener('click', HTMLController$onClick$lambda(this));
  };
  HTMLController.prototype.isStringAnEquation_0 = function (p_equation) {
    var tmp$;
    if (p_equation.length === 0)
      return false;
    var temp = true;
    tmp$ = iterator(p_equation);
    while (tmp$.hasNext()) {
      var character = unboxChar(tmp$.next());
      switch (character) {
        case 48:
        case 49:
        case 50:
        case 51:
        case 52:
        case 53:
        case 54:
        case 55:
        case 56:
        case 57:
        case 40:
        case 41:
        case 45:
        case 43:
        case 42:
        case 47:
        case 37:
        case 94:
          temp = true;
          break;
        default:return false;
      }
    }
    return temp;
  };
  HTMLController.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'HTMLController',
    interfaces: []
  };
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  function Stack() {
    this.elements = ArrayList_init();
  }
  Stack.prototype.isEmpty = function () {
    return this.elements.size === 0;
  };
  Stack.prototype.size = function () {
    return this.elements.size;
  };
  Stack.prototype.push_11rb$ = function (item) {
    return this.elements.add_11rb$(item);
  };
  Stack.prototype.pop = function () {
    var item = lastOrNull_0(this.elements);
    if (!this.isEmpty()) {
      this.elements.removeAt_za3lpa$(this.elements.size - 1 | 0);
    }
    return item;
  };
  Stack.prototype.peek = function () {
    return lastOrNull_0(this.elements);
  };
  Stack.prototype.replaceTop_11rb$ = function (input) {
    var lastIndex = get_lastIndex(this.elements);
    this.elements.set_wxm5ur$(lastIndex, input);
  };
  Stack.prototype.toString = function () {
    return this.elements.toString();
  };
  Stack.prototype.iterator = function () {
    return new Stack$StackIterator(this);
  };
  function Stack$StackIterator($outer) {
    this.$outer = $outer;
    this.current = 0;
  }
  Stack$StackIterator.prototype.remove = function () {
    this.$outer.elements.removeAt_za3lpa$(this.current);
  };
  Stack$StackIterator.prototype.hasNext = function () {
    return this.current < this.$outer.elements.size;
  };
  Stack$StackIterator.prototype.next = function () {
    var temp = this.$outer.elements.get_za3lpa$(this.current);
    if (this.current >= this.$outer.elements.size) {
      throw new IndexOutOfBoundsException('Too large index');
    }
     else {
      this.current = this.current + 1 | 0;
    }
    return temp;
  };
  Stack$StackIterator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'StackIterator',
    interfaces: [MutableIterator]
  };
  Stack.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Stack',
    interfaces: [MutableIterable]
  };
  var package$com = _.com || (_.com = {});
  var package$lab = package$com.lab || (package$com.lab = {});
  package$lab.Calculator = Calculator;
  package$lab.main_kand9s$ = main;
  package$lab.HTMLController = HTMLController;
  Stack.StackIterator = Stack$StackIterator;
  package$lab.Stack = Stack;
  main([]);
  Kotlin.defineModule('calc', _);
  return _;
}(typeof calc === 'undefined' ? {} : calc, kotlin);

//# sourceMappingURL=calc.js.map
