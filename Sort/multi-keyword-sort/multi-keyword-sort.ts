/**
 * @param array: the input array
 * @return: the sorted array
 */
const multiSort = function (array) {
  const updatedArray = array.sort((a, b) => {
    if (a[1] === b[1]) {
      return a[0] - b[0];
    }

    return b[1] - a[1];
  });
  return updatedArray;
};
