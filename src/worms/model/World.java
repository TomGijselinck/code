package worms.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.lang.Math;

import worms.model.programs.Program;
import static worms.util.Util.*;
import be.kuleuven.cs.som.annotate.*;

/**
 * ...
 * 	 
 * @invar	Each world has a proper projectile.
 * 		  |	hasProperProjectile()
 * @invar	The worms attached to each world must be proper worms for that 
 * 			world.
 * 		  |	hasProperWorms()
 * 
 * @author 	Tom Gijselinck
 * @version	1.0
 *
 */
public class World {
	
	//CONSTRUCTORS
	
	/**
	 * ...
	 * 
	 * @param 	width
	 * 			...
	 * @param 	height
	 * 			...
	 * @param 	passableMap
	 * 			...
	 * @post	...
	 * 		  |	new.getWidth() == width
	 * @post	...
	 * 		  |	new.getHeight() == height
	 * @post	...
	 * 		  |	new.getPassableMap() == passableMap
	 * @throws	IllegalArgumentException
	 * 		  	...
	 * 		  |	! isValidWidth(width)
	 * @throws	IllegalArgumentException
	 * 		  	...
	 * 		  |	! isValidHeight(height)
	 */
	public World(double width, double height, boolean[][] passableMap,
			Random random) {
		this.width = width;
		this.height = height;
		this.passableMap = passableMap;
		isTerminated = false;
		horizontalResolution = getNoHorizontalPixels() / getWidth();
		verticalResolution = getNoVerticalPixels() / getHeight();
		horizontalScale = getWidth() / getNoHorizontalPixels();
		verticalScale = getHeight() / getNoVerticalPixels();
		pixelWidth = getWidth() / getNoHorizontalPixels();
		pixelHeight = getHeight() / getNoVerticalPixels();
	}
	
	
	
	
	//DESTRUCTOR
	/**
	 * Terminate this world.
	 * 
	 * @post	...
	 * 		  |	new.isTerminated()
	 * @post	This world no longer references a projectile as the projectile
	 * 		  	to which it is attached.
	 * 		  |	new.getProjectile() == null
	 * @post	If this world was not already terminated and if this world had
	 * 			an effective projectile, the projectile to which this world was
	 * 			attached no longer has a world attached to it.
	 * 		  |	if ((! this.isTerminated()) && (getProjectile != null))
	 * 		  |		then (! ((new) this.getProjectile()).hasWorld() )
	 * @post	No worms are any longer attached to this world.
	 * 		  |	new.getNbWorms() == 0
	 * @post	...
	 * 		  |	if (this.hasAsWorm(worm))
	 * 		  |		then ((new worm).getWorld() == null)
	 * @effect	Each worm attached to this world is removed from this world.
	 * 		  |	for each worm in getAllWorms():
	 * 		  |		this.removeAsWorm(worm)
	 */
	public void terminate() {
		if ((! this.isTerminated()) && (getProjectile() != null)) {
			getProjectile().setWorld(null);
		}
		setProjectile(null);
		Iterator<Worm> iterator = worms.iterator();
		while (iterator.hasNext()) {
			Worm worm = iterator.next();
			if (hasAsWorm(worm)) {
				worm.setWorld(null);
			}
			iterator.remove();
		}
		isTerminated = true;
	}
	
	/**
	 * Check whether this world is terminated.
	 */
	public boolean isTerminated() {
		return isTerminated;
	}
	
	/**
	 * Variable registering whether this world is terminated.
	 */
	private boolean isTerminated = false;
	
	
	
	
	/**
	 * Return the width of this world.
	 */
	@Basic
	public double getWidth() { 
		return width;
	}
	
	/**
	 * Checks whether the given width is a valid width for any world.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		width <= World.getWidthUpperBound()
	 * 		  |		&& width > 0
	 */
	public boolean isValidWidth() { return true;}
	
	/**
	 * 
	 */
	private double width;
	
	
	
	
	/**
	 * Return the width upper bound for any world.
	 */
	@Basic
	public static double getWidthUpperBound() { 
		return widthUpperBound;
	}
	
	/**
	 * Variable registering the width upper bound of any world.
	 */
	private static double widthUpperBound = Double.MAX_VALUE;
	
	
	
	
	/**
	 * Return the height of this world.
	 */
	@Basic
	public double getHeight() { 
		return height;
	}
	
	/**
	 * Checks whether the given height is a valid height for any world.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		height <= World.getHeightUpperBound()
	 * 		  |		&& height > 0
	 */
	public boolean isValidHeight() { return true;}
	
	/**
	 * 
	 */
	private double height;
	
	
	
	
	/**
	 * Return the height upper bound for any world.
	 */
	@Basic
	public static double getheighthUpperBound() { 
		return heightUpperBound;
	}
	
	/**
	 * Variable registering the height upper bound of any world.
	 */
	private static double heightUpperBound = Double.MAX_VALUE;
	
	
	
	
	/**
	 * Return the passable map of this world.
	 * 	A passable map consists of rows and columns of pixels presented as a
	 * 	matrix.
	 */
	public boolean[][] getPassableMap() { 
		return passableMap;
	}
	
	/**
	 * Return the number of horizontal pixels of the passable map of this world.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		getPassableMap().length
	 */
	public int getNoHorizontalPixels() {
		return getPassableMap()[0].length;
	}
	
	/**
	 * Return the number of vertical pixels of the passable map of this world.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		getPassableMap()[0].length
	 */
	public int getNoVerticalPixels() { 
		return getPassableMap().length;
	}

	/**
	 * Variable referencing the passable map of this world.
	 */
	private boolean[][] passableMap;
	
	
	
	
	/**
	 * Return the horizontal resolution of this world in map pixels per 
	 * worm-meter.
	 */
	@Basic
	@Immutable
	public double getHorizontalResolution() { 
		return horizontalResolution;
	}
	
	private final double horizontalResolution;
	
	/**
	 * Return the vertical resolution of this world in map pixels per 
	 * worm-meter.
	 */
	@Basic
	@Immutable
	public double getVerticalResolution() { 
		return verticalResolution;
	}
	
	private final double verticalResolution;
	
	/**
	 * Return the horizontal scale of this world in worm-meter per map pixel.
	 */
	@Basic
	@Immutable
	public double getHorizontalScale() {
		return horizontalScale;
	}
	
	private final double horizontalScale;
	
	/**
	 * Return the vertical scale of this world in worm-meter per map pixel.
	 */
	@Basic
	@Immutable
	public double getVerticalScale() {
		return verticalScale;
	}
	
	private final double verticalScale;
	
	/**
	 * Return the height of each pixel of the passable map of this world in 
	 * metre.
	 */
	@Basic
	@Immutable
	public double getPixelWidth() { 
		return pixelWidth;
	}
	
	private final double pixelWidth;
	
	/**
	 * Return the height of each pixel of the passable map of this world in 
	 * metre.
	 */
	@Basic
	@Immutable
	public double getPixelHeight(){ 
		return pixelHeight;
	}
	
	private final double pixelHeight;
	
	
	
	
	/**
	 * Return the row of pixels in the passable map of this world where the 
	 * given position is located.
	 * 	The row is selected by the Y coordinate of the given position.
	 * 
	 * @return	...
	 * 		  |	getNoVerticalPixels() - (int) position.getY() * getVerticalResolution()
	 */
	public int getPixelRow(Position position) {
		return (getNoVerticalPixels() - (int) (position.getY() * getVerticalResolution()));
	}
	
	/**
	 * Return the column of pixels in the passable map of this world where the 
	 * given position is located.
	 * 	The column is selected by the X coordinate of the given position.
	 * 
	 * @return	...
	 * 		  |	(int) position.getX() * getHorizontalResolution() + 1
	 */
	public int getPixelColumn(Position position) { 
		return (int) (position.getX() * getHorizontalResolution() + 1);
	}
	
	
	
	
	//PASSABILITY (total)
	/**
	 * Check whether the given position is passable.
	 * 
	 * @return	...
	 * 		  |	let
	 * 		  |		passableMap = getPassableMap()
	 * 		  |		&& x = getPixelRow(position) - 1
	 * 		  |		&& y = getPixelColumn(position) - 1
	 * 		  |	in
	 * 		  |		passableMap[x][y] == true
	 */
	public boolean isPassable(Position position) {
		double x = position.getX();
		double y = position.getY();
		double rx = x%getPixelWidth();
		double ry = y%getPixelHeight();
		if (fuzzyEquals(rx, 0) && fuzzyEquals(ry, 0)) {
			//check boven, onder, links en rechts
			double dx = getPixelWidth()/2;
			double dy = getPixelHeight()/2;
			Position topLeft = new Position(x - dx, y + dy);
			Position topRight = new Position(x + dx, y + dy);
			Position bottomLeft = new Position(x - dx, y - dy);
			Position bottomRight = new Position(x + dx, y - dy);
			boolean topLeftPassable = isPassable(topLeft);
			boolean topRightPassable = isPassable(topRight);
			boolean bottomLeftPassable = isPassable(bottomLeft);
			boolean bottomRightPassable = isPassable(bottomRight);
			if (topLeftPassable && topRightPassable) {
				return true;
			} else if (topRightPassable && bottomRightPassable) {
				return true;
			} else if (bottomRightPassable && bottomLeftPassable) {
				return true;
			} else if (bottomLeftPassable && topLeftPassable) {
				return true;
			} else {
				return false;
			}
		} else if (fuzzyEquals(rx, 0) && (! fuzzyEquals(ry, 0))) {
			//check links en rechts
			double dx = getPixelWidth()/2;
			Position left = new Position(x-dx, y);
			Position right = new Position(x+dx, y);
			boolean leftImpassable = isImpassable(left);
			boolean rightImpassable = isImpassable(right);
			if (leftImpassable && rightImpassable) {
				return false;
			} else {
				return true;
			}
		} else if (fuzzyEquals(ry, 0) && (!fuzzyEquals(rx, 0))) {
			//check boven en onder
			double dy = getPixelHeight()/2;
			Position top = new Position(x, y+dy);
			Position bottom = new Position(x, y-dy);
			boolean topImpassable = isImpassable(top);
			boolean bottomImpassable = isImpassable(bottom);
			if (topImpassable && bottomImpassable) {
				return false;
			} else {
				return true;
			}
		} else {
			//normaal
			int X = getPixelRow(position) - 1;
			int Y = getPixelColumn(position) - 1;
			if (! isInsideWorldBorders(position)) {
				// outside world borders
				return true;
			} else {
				return (getPassableMap()[X][Y] == true);
			}
		}
		
	}
	
	/**
	 * 
	 * @param position
	 * @return
	 */
	public boolean isInsideWorldBorders(Position position) {
		double X = position.getX();
		double Y = position.getY();
		if ((X < 0) || (X > getWidth())
				|| (Y < 0) || (Y > getHeight()) ) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 *...
	 *
	 * @param	position
	 *			...
	 * @param	radius
	 * 			...
	 * @return	...
	 * 		  |	let
	 * 		  |		top = position.translate(0, radius);
	 *		  |		bottom = position.translate(0, -radius);
	 *		  |		left = position.translate(-radius, 0);
	 *		  |		right = position.translate(radius, 0);
	 *		  |	in
	 *		  |		result ==
	 *		  |			 ( isInsideWorldBorders(top) 
	 *		  |			&& isInsideWorldBorders(bottom)
	 *		  |			&& isInsideWorldBorders(left) 
	 *		  |			&& isInsideWorldBorders(right) )
	 */
	public boolean objectIsInsideWorldBorders(Position position, double radius) {
		Position top = position.translate(0, radius);
		Position bottom = position.translate(0, -radius);
		Position left = position.translate(-radius, 0);
		Position right = position.translate(radius, 0);
		
		if (isInsideWorldBorders(top) && isInsideWorldBorders(bottom)
				&& isInsideWorldBorders(left) && isInsideWorldBorders(right)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check whether the given position is impassable.
	 * 
	 * @param 	location
	 * 			...
	 * @effect	True if the given position is not passable.
	 * 		  |	result == (! isPassable(position))
	 */
	public boolean isImpassable(Position position) { 
		return (! isPassable(position));
	}
	
	/**
	 * ...
	 * 
	 * @param 	position
	 * 			...
	 * @param 	outerRadius
	 * 			...
	 * @param 	innerRadius
	 * 			...
	 * @return	...
	 * 		  | if (for each radius in innerRadius..outerRadius:
	 * 		  |		  for each angle in 0..2*Pi:
	 * 		  |			(getPassableMap()[radius*cos(angle)][radius*sin(angle)]
	 * 		  |				== true) )
	 * 		  |	then result == true
	 */
	public boolean isPassableArea(Position position, double outerRadius,
			double innerRadius) {
		double dr;
		double dtheta;
		double angle = 0;
		double radius;
		double x;
		double y;
		double x0 = position.getX();
		double y0 = position.getY();
		
		if (getHorizontalScale() >= getVerticalScale()) {
			dr = getHorizontalScale();
		} else {
			dr = getVerticalScale();
		}
		
		//Basic checkpoints
		for (int i = 1; i <= 4; i++) {
			double alfa = Math.PI * i / 2;
			x = outerRadius * Math.cos(alfa) + x0;
			y = outerRadius * Math.sin(alfa) + y0;
			if (isImpassable(new Position(x, y))) {
				return false;
			}
		}
		
		radius = innerRadius;
		while  (radius < outerRadius){
			dtheta = dr/radius;
			angle = 0;
			while  (angle < 2*Math.PI){
				x = radius * Math.cos(angle) + x0;
				y = radius * Math.sin(angle) + y0;
				if (isImpassable(new Position(x, y))) {
					return false;
				}
				angle = angle + dtheta;
			}
			radius = radius + dr;
			
		}
		dtheta = dr/outerRadius;
		angle = 0;
		while  (angle < 2*Math.PI) {
			x = outerRadius * Math.cos(angle) + x0;
			y = outerRadius * Math.sin(angle) + y0;
			if (isImpassable(new Position(x, y))) {
				return false;
			}
			angle = angle + dtheta;
		}
			
		return true;
	}
	
	/**
	 * ...
	 * 
	 * @param 	position
	 * 			...
	 * @param 	outerRadius
	 * 			...
	 * @param 	innerRadius
	 * 			...
	 * @effect	...
	 * 		  |	result == (! isPassableArea(position, outerRadius, innerRadius))
	 */
	public boolean isImpassableArea(Position position, double outerRadius,
			double innerRadius) {
		return (! isPassableArea(position, outerRadius, innerRadius));
	}
	
	/**
	 * Check whether the given position is passable for an object with the given
	 * radius.
	 * 
	 * @param 	position
	 * 			...
	 * @param 	radius
	 * 			...
	 * @effect	...
	 * 		  |	result == isPassableArea(position, radius, 0)
	 * 
	 */
	public boolean isPassableForObject(Position position, double radius) {
		return isPassableArea(position, radius, 0);
	}
	
	/**
	 * ...
	 * 
	 * @param 	position
	 * 			...
	 * @param 	radius
	 * 			...
	 * @return	...
	 * 		  |	result == (! isPassableArea(position, radius, 0))
	 */
	public boolean isImpassableForObject(Position position, double radius) {
		return (! isPassableForObject(position, radius));
	}
	
	/**
	 * Checks whether the given position is passable and adjacent to an
	 * impassable position for an object with the given radius.
	 * 
	 * @param	position
	 * 			...
	 * @param	radius
	 * 			... 
	 * @effect	...
	 * 		  |	result ==
	 * 		  |		(isPassableForObject(position, radius)
	 * 		  |	   && isImpassableArea(position, 1.1*radius, radius)
	 * 		  |	   && objectInsideWorldBorders(position, radius) )
	 */
	public boolean isAdjacent(Position position, double radius) {
		double outerRadius = 1.1 * radius;
		return (isPassableForObject(position, radius)
				&& isImpassableArea(position, outerRadius, radius)
				&& objectInsideWorldBorders(position, radius));
	}
	
	/**
	 * ...
	 * 
	 * @param 	position
	 * 			...
	 * @param 	radius
	 * 			...
	 * @return	...
	 * 		  |	result ==
	 * 		  |		for angle in 0..2*Pi:
	 * 		  |			isPassable(New Position(getRadius * cos(angle), 
	 * 		  |				getRadius * sin(angle)))
	 */
	public boolean objectInsideWorldBorders(Position position, double radius) {
		double x;
		double y;
		double x0 = position.getX();
		double y0 = position.getY();
		
		for (int i = 1; i <= 4; i++) {
			double alfa = Math.PI * i / 2;
			x = radius * Math.cos(alfa) + x0;
			y = radius * Math.sin(alfa) + y0;
			if (isImpassable(new Position(x, y))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * ...
	 * 
	 * @param 	position
	 * 			...
	 * @param 	radius
	 * 			...
	 * @return	...
	 * 		  |	if for some worm in getAllWorms():
	 * 		  |		position.getDistanceFrom(worm.getPosition()) 
	 * 		  |			< (radius + worm.getRadius())
	 * 		  |		then result == true
	 * 		  |	else result == false
	 */
	public boolean overlaps(Position position, double radius) {
		Iterator<Worm> iterator = getAllWorms().iterator();
		while (iterator.hasNext()) {
			Worm worm = iterator.next();
			if (position.getDistanceFrom(worm.getPosition())
					< (radius + worm.getRadius())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ...
	 * 
	 * @param 	position
	 * 			...
	 * @param 	radius
	 * 			...
	 * @return	...
	 * 		  |	if for some worm in getAllWorms():
	 * 		  |		position.getDistanceFrom(worm.getPosition()) 
	 * 		  |			< (radius + worm.getRadius())
	 * 		  |		then result == worm
	 * 		  |	else result == null
	 */
	public Worm getOverlappingWorm(Position position, double radius) {
		Iterator<Worm> iterator = getAllWorms().iterator();
		while (iterator.hasNext()) {
			Worm worm = iterator.next();
			if (position.getDistanceFrom(worm.getPosition())
					< (radius + worm.getRadius())) {
				return worm;
			}
		}
		return null;
	}
	
	
	
	
	//ASSOCIATIONS (defensive)
	/**
	 * Return the live projectile of this world. 
	 */
	@Basic
	public Projectile getProjectile() { 
		return projectile;
	}
	
	/**
	 * Checks whether this world can have the given projectile as its 
	 * projectile.
	 * 
	 * @param	projectile
	 * 			...
	 * @return	...
	 * 		  |	if (isTerminated())
	 * 		  |		then result == (projectile == null)
	 * 		  |	else result ==
	 * 		  |		( ( (projectile != null)
	 * 		  |	     && (! projectile.isTerminated()) )
	 * 		  |    || (projectile == null) )
	 */
	public boolean canHaveAsProjectile(Projectile projectile) {
		if (isTerminated()) {
			return (projectile == null);
		} else {
			return ( (projectile == null)
				  || ( (projectile != null)
					&& (! projectile.isTerminated())) );
		}
	}
	
	/**
	 * Check whether this world has a proper projectile attached to it.
	 * 	Check for consistency of mutual reference implied by bidirectional
	 * 	association.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		( canHaveAsProjectile(getProjectile())
	 * 		  |	   && ( (getProjectile() == null)
	 * 		  |		 ||	(getProjectile().getWorld() == this) ) )
	 */
	public boolean hasProperProjectile() {
		return ( canHaveAsProjectile(getProjectile())
			  &&  ( (getProjectile() == null)
				 ||	(getProjectile().getWorld() == this)) ); 
	}
	
	/**
	 * Set the projectile which is active in this world to the given projectile.
	 * 
	 * @param 	projectile
	 * 			...
	 * @post	...
	 * 		  |	new.getProjectile() == projectile
	 * @post	...
	 * 		  |	if (projectile != null)
	 * 		  |		then new.getProjectile().getWorld() == this
	 * 		  |	else
	 * 		  |		if (this.getProjectile() != null)
	 * 		  |			then (new this.getProjectile()).getWorld() == null
	 * @throws	IllegalArgumentException
	 * 			...
	 * 		  |	! canHaveAsProjectile(projectile)
	 */
	public void setProjectile(Projectile projectile) 
			throws IllegalArgumentException {
		if (! canHaveAsProjectile(projectile)) {
			throw new IllegalArgumentException();
		}
		if (projectile != null) {
			this.projectile = projectile;
			getProjectile().setWorld(this);
		} else {
			if (getProjectile() != null) {
				getProjectile().setWorld(null);
				this.projectile = null;
			}
		}
		
	}
	
	/**
	 * Checks whether this world has a live effective projectile attached to it.
	 * 
	 * @return	...
	 * 		  |	result == (getProjectile() != null)
	 */
	public boolean hasProjectile() {
		return (getProjectile() != null);
	}
	
	/**
	 * Variable referencing the live projectile in this world. 
	 */
	private Projectile projectile;
	
	/**
	 * Check whether this world has the given worm as one of the worms attached
	 * to it.
	 * 
	 * @param 	worm
	 * 			...
	 */
	@Basic
	public boolean hasAsWorm(Worm worm) {
		return worms.contains(worm);
	}
	
	/**
	 * Check whether this world can have the given worm as one of its worms.
	 * 
	 * @param 	worm
	 * 			...
	 * @return	Returns false if the given worm is not effective.
	 * 		  |	if (worm == null)
	 * 		  |		then result == false
	 * 			Otherwise, true if and only if this world is not yet terminated,
	 * 			the given worm is not yet terminated and the position of the
	 * 			given worm is a valid position for that worm in this world.
	 * 		  |	else result ==
	 * 		  |		( (! this.isTerminated())
	 * 		  |	   && (! worm.isTerminated())
	 * 		  |	   && this.isAdjacent(worm.getPosition(), worm.getRadius()) )
	 */
	public boolean canHaveAsWorm(Worm worm) {
		if (worm == null) return false;
		else return ( (! this.isTerminated())
				&& (! worm.isTerminated())
				&& this.isAdjacent(worm.getPosition(), worm.getRadius()) );
	}
	
	/**
	 * Check whether this world has proper worms attached to it.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		for each worm in Worm:
	 * 		  |			( if (this.hasAsWorm(worm))
	 * 		  |				then canHaveAsWorm(worm)
	 * 		  |				  && (worm.getWorld() == this) )
	 */
	public boolean hasProperWorms() {
		Iterator<Worm> iterator = worms.iterator();
		while (iterator.hasNext()) {
			Worm worm = iterator.next();
			if (hasAsWorm(worm)) {
				if ( (! canHaveAsWorm(worm))
				  || (worm.getWorld() != this)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * ...
	 */
	@Basic
	public int getNbWorms() {
		return getAllWorms().size();		
	}
	
	/**
	 * Return the index at which the given worm is registered as a worm for this
	 * world.
	 * 
	 * @param 	worm
	 * 			The worm to look for.
	 * @return	...
	 * 		  |	result == getAllWorms().getIndexOf(worm)
	 */
	public int getIndexOfWorm(Worm worm) {
		return worms.indexOf(worm);
	}
	
	/**
	 * ...
	 * 
	 * @param 	index
	 * 			...
	 * @return	...
	 * 		  |	getWormAt(result) == worm
	 * @throws	IndexOutOfBoundException
	 * 			...
	 * 		 |	(index < 0) || (index > getNbWorms() - 1)
	 */
	@Basic
	public Worm getWormAt(int index) throws IndexOutOfBoundsException {
		return getAllWorms().get(index);
	}
	
	public List<String> getAllWormNames() {
		List<Worm> worms = getAllWorms();
		List<String> allWormNames = new ArrayList<>();
		Iterator<Worm> iterator = worms.iterator();
		while (iterator.hasNext()) {
			Worm worm = iterator.next();
			allWormNames.add(worm.getName());
		}
		return allWormNames;
	}
	
	
	/**
	 * Return a list collecting all worms associated with this world.
	 * 
	 * @return	...
	 * 		  |	result != null
	 * @return	...
	 * 		  |	for each worm in Worm:
	 * 		  |		result.contains(worm) ==
	 * 		  |		this.hasAsWorm(worm)
	 */
	public List<Worm> getAllWorms() {
		return worms;
	}
	
	/**
	 * Add the given worm to the list of worms attached to this world.
	 * 
	 * @param 	worm
	 * 			...
	 * @post	...
	 * 		  |	new.hasAsWorm(worm)
	 * @post	...
	 * 		  |	(new worm).getWorld() == this
	 * @throws	IllegalArgumentException
	 * 			...
	 * 		  |	! canHaveAsWorm(worm)
	 * @throws	IllegalArgumentException
	 * 			The given worm is already attached to some world.
	 * 		  |	 ( (worm != null)
	 * 		  | && (worm.getWorld() != null) )
	 */
	public void addAsWorm(Worm worm) {
		if (! canHaveAsWorm(worm)) throw new IllegalArgumentException();
		if ((worm != null) && (worm.getWorld() != null))
			throw new IllegalArgumentException();
		worm.setWorld(this);
		worms.add(worm);
	}
	
	/**
	 * Remove the given worm from the list of worms attached to this world.
	 * 
	 * @param 	worm
	 * 			...
	 * @post	...
	 * 		  |	! new.hasAsWorm(worm)
	 * @post	...
	 * 		  |	if (this.hasAsWorm(worm))
	 * 		  |		then ( ((new worm).getWorld() == null) 
	 * 		  |			&& ((new worm).isTerminated())
	 * @throws	IllegalArgumentException
	 * 			The given worm is not effective.
	 * 		  |	worm == null
	 */
	public void removeAsWorm(Worm worm) {
		if (worm == null) throw new IllegalArgumentException();
		if (hasAsWorm(worm)) {
			worm.setWorld(null);
			worm.terminate();
		}
		worms.remove(worm);
	}
	
	/**
	 * List collecting references to worms attached to this world.
	 * 
	 * @invar	...
	 * 		  |	worms != null
	 * @invar	...
	 * 		  |	for each worm in Worm:
	 * 		  |		canHaveAsWorm(worm)
	 * @invar	...
	 * 		  |	for each worm in Worm:
	 * 		  |		(worm.getWorld() == this)
	 */
	private final List<Worm> worms = new ArrayList<Worm>();
	
	/**
	 * Return the projectile and all the worms attached to this world
	 * 
	 * @return	The resulting collection contains the projectile attached to 
	 * 			this world if any, and all worms attached to this world.
	 * 		  | (if (hasProjectile()) 
	 * 		  |		then result.contains(getProjectile()) )
	 * 		  | && ( for each worm in worms:
	 * 		  |		 result.contains(worm) )
	 */
	public Set<Object> getAllWorldObjects() {
		final Set<Object> allObjects = new HashSet<Object>();
		allObjects.addAll(worms);
		if (hasProjectile()) allObjects.add(projectile);
		return allObjects;
	}
	
	
	
	
	//GAME
	public void startGame() {		
		gameStarted = true;
		int index = 0;
		setCurrentWorm(getAllWorms().get(index));
		while (getCurrentWorm().hasProgram()) {			
			getCurrentWorm().getProgram().run();
			index += 1;
			setCurrentWorm(getAllWorms().get(index));
		}
	}
	
	public void startNextTurn() {
		if (! isGameFinished()) {
			int index = worms.indexOf(getCurrentWorm());
			if (index >= worms.size() - 1) {
				index = 0;
			} else {
				index += 1;
			}
			setCurrentWorm(worms.get(index));
			Worm worm = getCurrentWorm();
			worm.setCurrentActionPoints(worm.getActionPointsMaximum());
			worm.addHitPoints(10);
			if (getCurrentWorm().hasProgram()) {
				getCurrentWorm().getProgram().run();
			}
		}
	}
	
	public void addWorm(Program program) {
		if (! isGameStarted()) {
			int max = getAllWorms().size();
			int index = randomInt(0, max);
			Collections.shuffle(Arrays.asList(nameList));
			String name = getNameAt(index);
			List<String> currentWormNames = getAllWormNames();
			while (currentWormNames.contains(name)) {
				index = randomInt(0, max);
				name = getNameAt(index);
			}
			Worm newWorm = new Worm(new Position(0, 0), 0, 1, name);
			
			//find possible position
			double minRadius = newWorm.getLowerRadiusBound();
			double maxRadius = 4 * minRadius;
			newWorm.setRadius(randomDouble(minRadius, maxRadius));
			
			double direction = randomDouble(Worm.getLowerAngleBound(),
					Worm.getUpperAngleBound());
			newWorm.setDirection(direction);
			
			Position position = searchAdjacentPosition(newWorm);
			while (position == null) {
				position = searchAdjacentPosition(newWorm);
			}
			newWorm.setPosition(position);
			newWorm.setProgram(program);
			
			addAsWorm(newWorm);
		}
	}
	
	private Position searchAdjacentPosition(Worm worm) {
		double R = worm.getRadius();
		double x = randomDouble(0, getWidth());
		double y = randomDouble(0, getHeight());
		Position position = new Position(x, y);
		
		double x0 = getWidth() / 2.0;
		double y0 = getHeight() / 2.0;
		double angle = Math.atan((y0 - y) / (x0 - x));
		double ds = 0.1 * worm.getRadius();
		
		while ((! isAdjacent(position, R)) && isInsideWorldBorders(position)) {
			double dx = ds * Math.cos(angle);
			double dy = ds * Math.sin(angle);
			position = position.translate(dx, dy);
		}
		
		if (! isInsideWorldBorders(position)){
			return null;
		} else {
			return position;
		}
	}
	
	public Worm getWinningWorm() {
		Worm winningWorm;
		Iterator<Worm> iterator = worms.iterator();
		winningWorm = iterator.next();
		while (iterator.hasNext()) {
			Worm otherWorm = iterator.next();
			if (otherWorm.getCurrentHitPoints() > winningWorm
					.getCurrentHitPoints()) {
				winningWorm = otherWorm;
			}
		}
		return winningWorm;
	}
	
	public boolean isGameFinished() {
		if (worms.size() == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	public boolean isGameStarted() {
		return gameStarted;
	}
	
	private boolean gameStarted = false;
	
	
	
	
	public Worm getCurrentWorm() {
		return currentWorm;
	}
	
	private void setCurrentWorm(Worm worm) {
		currentWorm = worm;
	}
	
	private Worm currentWorm;
	
	private String getNameAt(int index) {
		return nameList[index];
	}
	
	public String[] getAllNames() {
		return nameList;
	}
	
	private String[] nameList = {"The Beast", "The Crippler", "Babyface",
			"Bad Boy", "Sugar", "KO", "The Technician", "Kombo King", 
			"The Assassin", "Black Widow", "Crazy Legs", "Pitbull", "Bulldog",
			"The Warrior", "The Warhammer", "The Hammer", "Gangsta", 
			"The Iceman", "Mr Freeze", "The Monster", "Man of Stone", 
			"Godzilla", "King Kong", "Red Hot", "Thunder", "Meltdown",
			"The Nightmare", "El Bandit", "The Capitalizer", "Monkey Man",
			"Wizard", "Dragoon", "Reaper", "Ninpo", "Gremlin", "Thunderball",
			"The Ultimate", "Magician", "Warlord", "The Surprise Package", 
			"Tough Guy", "The Reaper", "The Fallen", "Unforgiven", 
			"The Country Dream", "King", "Queen", "Moonwalker", "Colossal", 
			"Fastball", "Speed Demon", "One Punch", "Glass Jaw", 
			"Hands Of Steel", "Shining Wizard", "Pure Grit", "Celtic Wizard",
			"True Born", "The Next Level", "The Finest", "Pure", "Solo", 
			"Dragon", "Solid", "Tainted", "Senator", "Sinister"};
	
}
