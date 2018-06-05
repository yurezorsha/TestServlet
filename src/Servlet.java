

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
//import estimator.ExpressionEstimator;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.getRequestDispatcher("index.jsp").include(req, resp);
		OutputStream out = null;
		String name=req.getParameter("function");
		String x0=req.getParameter("X0");
		String x=req.getParameter("X");
		
		XYSeriesCollection xyDataset = new XYSeriesCollection();
		
		XYSeries series = new XYSeries(name);
		XYSeries series2 = new XYSeries("");
	    
	    
		ExpressionEstimator est=new ExpressionEstimator();
		double inf=1.0/0.0;
		double infi=-(1.0/0.0);
		Result r=new Result(); 
		float k=0;
		System.out.println("Выражение: "+name);
	    outer: for(float i = Float.parseFloat(x0); i < Float.parseFloat(x); i+=0.1){
	    	String s=name;
	    	
			s=s.replaceAll("x", "("+Float.toString(i)+")");	
			try {
				System.out.println(ExpressionEstimator.calculate(s));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println(Float.parseFloat(r.result(s)));
                if ((Float.parseFloat(r.result(s)) ==inf) || (Float.parseFloat(r.result(s))==infi)){
                    k=i+1;
                    System.out.println("Yes");
                    break;
                		
                	}
                	//System.out.println(r.result(s));
                	
                	
                else {
				series.add(i, Float.parseFloat(r.result(s)));
                }
	    }
	    xyDataset.addSeries(series);
	    
	    if(k!=0) {
	    	
	    	
	    	for(float i = k; i < Float.parseFloat(x); i+=0.1){
	    		String s=name;
	    		s=s.replaceAll("x", "("+Float.toString(i)+")");	
	    		series2.add(i, Float.parseFloat(r.result(s)));	
	    	}
	    	
	    	
	    k=0; 	
	    }
	    xyDataset.addSeries(series2);
	    
	    
	    
	    
	    JFreeChart chart = ChartFactory
	        .createXYLineChart(name, "x", "y",
	                           xyDataset, 
	                           PlotOrientation.VERTICAL,
	                           true, true, true);
	    XYLineAndShapeRenderer renderer=new XYLineAndShapeRenderer();
	    final XYPlot plot = chart.getXYPlot();
	    
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesShapesVisible(0, false);
	    renderer.setSeriesPaint(0,Color.RED);
	    renderer.setSeriesPaint(1,Color.RED);
        plot.setRenderer(renderer);
	    
	
	resp.setContentType("image/png");
    // Экспорт диаграммы в PNG

    //FileOutputStream fos = new FileOutputStream("123.png");
    out = resp.getOutputStream();
    ChartUtilities.writeChartAsPNG(out, chart, 600, 400);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
		
	//doGet(req,resp);	
		
		
	}*/

}
