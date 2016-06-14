pathfile=File.openDialog("Choose the txt file to Open:");
filestring=File.openAsString(pathfile);
rows=split(filestring, "\n");
setBatchMode(true);
for(i=0; i<rows.length; i++){
lrg_path = rows[i];
open(lrg_path);
sml_path = replace(rows[i],"lrg-","sml-");
scale=0.55;
w=getWidth*scale; h=getHeight*scale;
run("Size...", "width=w height=h interpolation=Bilinear");
saveAs("png", sml_path);
close();
}
