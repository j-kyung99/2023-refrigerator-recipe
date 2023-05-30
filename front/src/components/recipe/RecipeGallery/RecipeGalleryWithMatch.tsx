import router from "next/router";
import { Cup, EyeFill, Percent, StarFill } from "react-bootstrap-icons";
import { RecipeBrief, BookmarkedRecipe } from "@/types";
import styles from "./RecipeGallery.module.scss";
import Stars from "../Stars/Stars";

type RecipeGalleryProps = {
	recipeData: RecipeBrief[] | BookmarkedRecipe[];
};

export default function RecipeGalleryWithMatch({
	recipeData,
}: RecipeGalleryProps) {
	const onRecipeClick = (recipeID: number) => {
		router.push(`/recipe/info?recipeID=${recipeID}`);
	};

	return (
		<div className={styles.recipeGalleryContainer}>
			{recipeData.map((recipe) => (
				<div
					key={recipe.recipeID}
					className={styles.recipeContainer}
					onClick={() => onRecipeClick(recipe.recipeID)}
				>
					<img src={recipe.image} />

					<div className={styles.recipeNameInfoContainer}>
						<div className={styles.recipeNameScoreCol}>
							<span className={styles.recipeName}>{recipe.recipeName}</span>
							<div className={styles.recipeInfo}>
								{recipe.scoreAvg !== 0
									? `★ ${recipe.scoreAvg.toFixed(1)}`
									: ` `}
							</div>
						</div>
						<div className={styles.recipeMatch}>{recipe.match} %</div>
					</div>
				</div>
			))}
		</div>
	);
}
