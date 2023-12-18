package d16;

record Point(int row, int col) {
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Point point)) return false;
		return row == point.row && col == point.col;
	}
}
