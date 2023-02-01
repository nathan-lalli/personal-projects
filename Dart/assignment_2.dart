void main() {
  // TO DO All 10 questions can be written in 1 line of code using the following syntax. Either:
  // 1) return list.[method(argument)];
  // 2) return list.[method(argument)].[method(argument)];
  // All methods I used are:
  // any, firstWhere, lastWhere, map, reduce, skip, take, toList, toSet, & where

  // NOTE: You may comment out or delete the imperative programming code including the assertions.
  // NOTE: This would be a good time to use https://dartpad.dev

  funWithFunctionalProgramming();
}

void funWithFunctionalProgramming() {
  final list = [2, 3, 4, 2, 6, 5];

  print(getMaxValue(list));
  print(getSumOfOddNumbers(list));
  print(lastThreeNumbers(list));
  print(listMultipliedByTwo(list));
  print(getFirstNumberGreaterThanFour(list));
  print(getFirstNNumbers(list, 3));
  print(getLastOddNumber(list));
  print(getAllUniqueNumbers(list));
  print(hasAnyNumberGreaterThanTen(list));
  print(getAllButFirstTwoElements(list));

  print('original: $list');
}

// int getMaxValue(List<int> list) {
//   assert(list.isNotEmpty);
//   int result = list.first;
//   for (int i = 0; i < list.length; i++) {
//     if (list[i] > result) {
//       result = list[i];
//     }
//   }
//   return result;
// }

int getMaxValue(List<int> list) {
  return list.fold(
      list.first, (previousValue, i) => i > previousValue ? i : previousValue);
}

// int getSumOfOddNumbers(List<int> list) {
//   int result = 0;
//   for (int i = 0; i < list.length; i++) {
//     if (list[i].isOdd) {
//       result += list[i];
//     }
//   }
//   return result;
// }

int getSumOfOddNumbers(List<int> list) {
  return list.where((i) => i.isOdd).reduce((value, i) => value + i);
}

// List<int> lastThreeNumbers(List<int> list) {
//   final List<int> result = [];

//   for (int i = list.length - 3; i < list.length; i++) {
//     result.add(list[i]);
//   }
//   return result;
// }

List<int> lastThreeNumbers(List<int> list) {
  return list.skip(list.length - 3).toList();
}

// List<int> listMultipliedByTwo(List<int> list) {
//   final List<int> result = [];

//   for (int i = 0; i < list.length; i++) {
//     result.add(list[i] * 2);
//   }
//   return result;
// }

List<int> listMultipliedByTwo(List<int> list) {
  return list.map((i) => i * 2).toList();
}

// int getFirstNumberGreaterThanFour(List<int> list) {
//   for (int i = 0; i < list.length; i++) {
//     if (list[i] > 4) {
//       return list[i];
//     }
//   }
//   throw ArgumentError('No number greater than 4 was provided in the list');
// }

int getFirstNumberGreaterThanFour(List<int> list) {
  return list.firstWhere((i) => i > 4);
}

// List<int> getFirstNNumbers(List<int> list, int n) {
//   final List<int> result = [];
//   for (int i = 0; i < n; i++) {
//     result.add(list[i]);
//   }
//   return result;
// }

List<int> getFirstNNumbers(List<int> list, int n) {
  return list.take(n).toList();
}

// int getLastOddNumber(List<int> list) {
//   int result;

//   for (int i = 0; i < list.length; i++) {
//     if (list[i].isOdd) {
//       result = list[i];
//     }
//   }

//   if (result == null) {
//     throw ArgumentError('No odd number was provided in the list');
//   }
//   return result;
// }

int getLastOddNumber(List<int> list) {
  return list.lastWhere((i) => i.isOdd);
}

// This doesn't need to guarantee any specific order of elements
// List<int> getAllUniqueNumbers(List<int> list) {
//   final set = Set<int>();
//   for (int i = 0; i < list.length; i++) {
//     set.add(list[i]);
//   }

//   final List<int> result = [];
//   for (int i = 0; i < set.length; i++) {
//     result.add(set.elementAt(i));
//   }
//   return result;
// }

List<int> getAllUniqueNumbers(List<int> list) {
  return list.toSet().toList();
}

// bool hasAnyNumberGreaterThanTen(List<int> list) {
//   for (int i = 0; i < list.length; i++) {
//     if (list[i] > 10) {
//       return true;
//     }
//   }
//   return false;
// }

bool hasAnyNumberGreaterThanTen(List<int> list) {
  return list.any((i) => i > 10);
}

// List<int> getAllButFirstTwoElements(List<int> list) {
//   final List<int> result = [];

//   for (int i = 2; i < list.length; i++) {
//     result.add(list[i]);
//   }
//   return result;
// }

List<int> getAllButFirstTwoElements(List<int> list) {
  return list.skip(2).toList();
}
