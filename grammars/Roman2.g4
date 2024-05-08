grammar Roman2;

num: thousands? hundreds? tens? units?;

units: ONE (ONE* | FIVE | TEN) | FIVE ONE*;
tens: TEN (TEN* | FIVE | HUNDRED) | FIFTY (TEN*);
hundreds: HUNDRED (HUNDRED* | FIVEHUNDRED | THOUSAND) | FIVEHUNDRED (HUNDRED*);
thousands: THOUSAND THOUSAND*;

ONE: 'I';
FIVE: 'V';
TEN: 'X';
FIFTY: 'L';
HUNDRED: 'C';
FIVEHUNDRED: 'D';
THOUSAND: 'M';