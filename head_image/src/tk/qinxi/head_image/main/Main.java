package tk.qinxi.head_image.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

public class Main {
	public static void main(String[] args) throws IOException {
		final List<String> list = new ArrayList<String>();//过滤文件后缀
		list.add("jpg");
		list.add("png");
		list.add("bmp");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileFilter() {//添加文件过滤器

			@Override
			public String getDescription() {
				return "image files";
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				String name = f.getName();
				int p = name.lastIndexOf('.');
				if (p == -1)
					return false;
				String suffix = name.substring(p + 1).toLowerCase();
				return list.contains(suffix);
			}
		});
		fileChooser.showOpenDialog(null);//选择文件
		File file = fileChooser.getSelectedFile();
		if (file == null) {//如果没有选择.退出程序
			System.exit(0);
		}
		BufferedImage img = ImageIO.read(file);
		Integer imgW = img.getWidth();
		// ///////////////////////
		Graphics2D graphics2d1 = (Graphics2D) img.createGraphics();
		graphics2d1.setColor(Color.RED);
		graphics2d1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿
		Ellipse2D.Double shape = new Ellipse2D.Double(imgW
				- (int) Math.rint(imgW * 0.3), 0, (int) Math.rint(imgW * 0.3),
				(int) Math.rint(imgW * 0.3));//生成圆形,参数为左,上,宽,高(宽高一样为圆形)
		graphics2d1.fill(shape);//红色填充圆形

		graphics2d1.setColor(Color.WHITE);
		String s = "1";
		graphics2d1.setFont(new Font("宋体", Font.BOLD, (int) Math
				.rint(imgW * 0.2)));

		graphics2d1.drawString(s, imgW - (int) Math.rint(imgW * 0.43) / 2,
				(int) Math.rint(imgW * 0.22));//画出"1"的位置,参数:字符串,左,上

		ImageIO.write(img, "jpg", new File(FileSystemView.getFileSystemView()
				.getHomeDirectory() + "/head_image.jpg"));//生成图片文件到桌面
		System.exit(0);//退出
	}
}
