package pl.grafiszti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Getter;

public class Board {
	private List<List<Boolean>> frame;

	@Getter
	private int size;

	public Board(int size) {
		this.size = size;
		this.frame = generateRandomFrame(size);
	}

	private List<List<Boolean>> getEmptyFrame(int size) {
		List<List<Boolean>> emptyFrame = new ArrayList<>();
		IntStream.range(0, size).forEach(i -> {
			List<Boolean> line = new ArrayList<>(Arrays.asList(new Boolean[size]));
			Collections.fill(line, Boolean.FALSE);
			emptyFrame.add(line);
		});
		return emptyFrame;
	}

	public boolean isSet(int x, int y) {
		return frame.get(x).get(y).equals(Boolean.TRUE);
	}

	public void update() {
		List<List<Boolean>> nextFrame = getEmptyFrame(size);

		IntStream.range(0, size).parallel().forEach(i -> IntStream.range(0, size).forEach(j -> {
			int currentNeighborCounter = countNeighbours(i, j);
			if (!isSet(i, j) && (currentNeighborCounter == 3)) {
				nextFrame.get(i).set(j, Boolean.TRUE);
			} else {
				if (isSet(i, j) && (currentNeighborCounter == 2 || currentNeighborCounter == 3)) {
					nextFrame.get(i).set(j, Boolean.TRUE);
				}
			}
		}));

		this.frame = nextFrame;
	}

	private int countNeighbours(int i, int j) {
		return isUpperLeftSet(i, j) + isUpperSet(i, j) + isUpperRightSet(i, j) + //
				isRightSet(i, j) + //
				isdownRightSet(i, j) + isDownSet(i, j) + isDownLeftSet(i, j) + //
				isLeftSet(i, j);
	}

	private int isLeftSet(int i, int j) {
		return j - 1 >= 0 && isSet(i, j - 1) ? 1 : 0;
	}

	private int isDownLeftSet(int i, int j) {
		return i + 1 < size && j - 1 >= 0 && isSet(i + 1, j - 1) ? 1 : 0;
	}

	private int isDownSet(int i, int j) {
		return i + 1 < size && isSet(i + 1, j) ? 1 : 0;
	}

	private int isdownRightSet(int i, int j) {
		return i + 1 < size && j + 1 < size && isSet(i + 1, j + 1) ? 1 : 0;
	}

	private int isRightSet(int i, int j) {
		return j + 1 < size && isSet(i, j + 1) ? 1 : 0;
	}

	private int isUpperRightSet(int i, int j) {
		return i - 1 >= 0 && j + 1 < size && isSet(i - 1, j + 1) ? 1 : 0;
	}

	private int isUpperSet(int i, int j) {
		return i - 1 >= 0 && isSet(i - 1, j) ? 1 : 0;
	}

	private int isUpperLeftSet(int i, int j) {
		return i - 1 >= 0 && j - 1 >= 0 && isSet(i - 1, j - 1) ? 1 : 0;
	}

	private List<List<Boolean>> generateRandomFrame(int n) {
		List<List<Boolean>> resultList = new ArrayList<>();
		IntStream.range(0, n).forEach(i -> resultList.add(generateRandomFrameLine(n)));
		return resultList;
	}

	private List<Boolean> generateRandomFrameLine(int size) {
		List<Boolean> line = new ArrayList<>();

		IntStream.range(0, size).forEach(i -> line.add(Boolean.FALSE));
		List<Integer> indexes = IntStream.range(0, size).boxed().collect(Collectors.toList());
		Collections.shuffle(indexes);
		indexes.subList(0, (int) (size * 0.2)).forEach(i -> line.set(i, Boolean.TRUE));

		return line;
	}
}
