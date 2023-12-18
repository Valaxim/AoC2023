package d16;

import java.util.List;

import static d16.Beam.BeamDirection.DOWN;
import static d16.Beam.BeamDirection.LEFT;
import static d16.Beam.BeamDirection.RIGHT;
import static d16.Beam.BeamDirection.UP;

record Beam(Point point, BeamDirection beamDirection) {
	
	public List<Beam> moveUp(char currentChar) {
		switch (currentChar) {
			case '/' -> {
				return List.of(new Beam(new Point(this.point.row(), this.point.col() + 1), RIGHT));
			}
			case '\\' -> {
				return List.of(new Beam(new Point(this.point.row(), this.point.col() - 1), LEFT));
			}
			case '-' -> {
				return List.of(
						new Beam(new Point(this.point.row(), this.point.col() - 1), LEFT),
						new Beam(new Point(this.point.row(), this.point.col() + 1), RIGHT));
			}
			default -> {
				return List.of(new Beam(new Point(this.point.row() - 1, this.point.col()), UP));
			}
		}
	}
	
	public List<Beam> moveDown(char currentChar) {
		switch (currentChar) {
			case '/' -> {
				return List.of(new Beam(new Point(this.point.row(), this.point.col() - 1), LEFT));
			}
			case '\\' -> {
				return List.of(new Beam(new Point(this.point.row(), this.point.col() + 1), RIGHT));
			}
			case '-' -> {
				return List.of(
						new Beam(new Point(this.point.row(), this.point.col() - 1), LEFT),
						new Beam(new Point(this.point.row(), this.point.col() + 1), RIGHT));
			}
			default -> {
				return List.of(new Beam(new Point(this.point.row() + 1, this.point.col()), DOWN));
			}
		}
	}
	
	public List<Beam> moveLeft(char currentChar) {
		switch (currentChar) {
			case '/' -> {
				return List.of(new Beam(new Point(this.point.row() + 1, this.point.col()), DOWN));
			}
			case '\\' -> {
				return List.of(new Beam(new Point(this.point.row() - 1, this.point.col()), UP));
			}
			case '|' -> {
				return List.of(
						new Beam(new Point(this.point.row() + 1, this.point.col()), DOWN),
						new Beam(new Point(this.point.row() - 1, this.point.col()), UP));
			}
			default -> {
				return List.of(new Beam(new Point(this.point.row(), this.point.col() - 1), LEFT));
			}
		}
	}
	
	public List<Beam> moveRight(char currentChar) {
		switch (currentChar) {
			case '/' -> {
				return List.of(new Beam(new Point(this.point.row() - 1, this.point.col()), UP));
			}
			case '\\' -> {
				return List.of(new Beam(new Point(this.point.row() + 1, this.point.col()), DOWN));
			}
			case '|' -> {
				return List.of(
						new Beam(new Point(this.point.row() - 1, this.point.col()), UP),
						new Beam(new Point(this.point.row() + 1, this.point.col()), DOWN));
			}
			default -> {
				return List.of(new Beam(new Point(this.point.row(), this.point.col() + 1), RIGHT));
			}
		}
	}
	
	public enum BeamDirection {
		RIGHT, LEFT, UP, DOWN
	}
	
}
